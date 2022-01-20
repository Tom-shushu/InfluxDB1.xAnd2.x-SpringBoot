package com.example.influxdb.influx.service;

import com.example.influxdb.influx.model.param.InsertParams;
import com.example.influxdb.influx.model.restlt.InfluxResult;
import com.influxdb.query.FluxTable;

import java.util.List;

/**
 * description: 时序数据库service
 * date: 2022/1/16 20:47
 * author: zhouhong
 */
public interface InfluxService {

    /**
      * description: InfluxDB插入
      * date: 2022/1/16 20:39
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    void insert(InsertParams insertParams);
    /**
     * description: 查询
     * date: 2022/1/16 20:39
     * author: zhouhong
     * @param  * @param null
     * @return
     */
    List<InfluxResult> queue();
}
