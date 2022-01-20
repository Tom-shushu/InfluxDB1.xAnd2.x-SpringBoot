package com.example.influxdb.influx.service.impl;

import com.example.influxdb.influx.config.InfluxBean;
import com.example.influxdb.influx.model.param.InsertParams;
import com.example.influxdb.influx.model.restlt.InfluxResult;
import com.example.influxdb.influx.service.InfluxService;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
  * description: 时序数据库Impl
  * date: 2022/1/16 20:47
  * author: zhouhong
  */
@Service
@Slf4j
public class InfluxServiceImpl implements InfluxService {

    @Resource
    private InfluxBean influxBean;
    @Override
    public void insert(InsertParams insertParams) {
        insertParams.setTime(Instant.now());
        influxBean.write(insertParams);
    }

    @Override
    public List<InfluxResult> queue(){
        // 下面两个 private 方法 赋值给 list 查询对应的数据
        List<FluxTable> list = queryInfluxAll();
        List<InfluxResult> results = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getRecords().size(); j++) {
                InfluxResult influxResult = new InfluxResult();
                influxResult.setCurrent(list.get(i).getRecords().get(j).getValues().get("current").toString());
                influxResult.setEnergyUsed(list.get(i).getRecords().get(j).getValues().get("energyUsed").toString());
                influxResult.setPower(list.get(i).getRecords().get(j).getValues().get("power").toString());
                influxResult.setVoltage(list.get(i).getRecords().get(j).getValues().get("voltage").toString());
                influxResult.setTime(list.get(i).getRecords().get(j).getValues().get("_time").toString());
                System.err.println(list.get(i).getRecords().get(j).getValues().toString());
                results.add(influxResult);
            }
        }
        return results;
    }
    /**
      * description: 查询一小时内的InsertParams所有数据
      * date: 2022/1/21 13:44
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    private List<FluxTable> queryInfluxAll(){
        String query = " from(bucket: \"tom\")" +
                "  |> range(start: -60m, stop: now())" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"influx_test\")" +
                "  |> pivot( rowKey:[\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\" )";
        return influxBean.queryTable(query);
    }
    /**
      * description: 根据某一个字段的值过滤(查询 用电量 energyUsed 为 322 的那条记录)
      * date: 2022/1/21 12:44
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    public List<FluxTable> queryFilterByEnergyUsed(){
        String query = " from(bucket: \"tom\")" +
                "  |> range(start: -60m, stop: now())" +
                "  |> filter(fn: (r) => r[\"_measurement\"] == \"influx_test\")" +
                "  |> filter(fn: (r) => r[\"energyUsed\"] == \"322\")" +
                "  |> pivot( rowKey:[\"_time\"], columnKey: [\"_field\"], valueColumn: \"_value\" )";
        return influxBean.queryTable(query);
    }
}
