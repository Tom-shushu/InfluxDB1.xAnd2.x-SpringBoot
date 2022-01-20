package com.example.influxdb.influx.controller;

import com.example.influxdb.influx.model.param.InsertParams;
import com.example.influxdb.influx.model.restlt.ResponseData;
import com.example.influxdb.influx.model.restlt.SuccessResponseData;
import com.example.influxdb.influx.service.InfluxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 控制类
 * date: 2022/1/16 22:58
 * author: zhouhong
 */
@RestController
public class InfluxDbController {
    @Autowired
    private InfluxService influxService;
    /**
      * description: 时许数据库插入测试
      * date: 2022/1/16 23:00
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    @PostMapping("/influxdb/insert")
    public ResponseData insert(@RequestBody InsertParams insertParams) {
        influxService.insert(insertParams);
        return new SuccessResponseData();
    }
    /**
     * description: 时序数据库查询全部数据测试
     * date: 2022/1/16 23:00
     * author: zhouhong
     * @param  * @param null
     * @return
     */
    @PostMapping("/influxdb/queryAll")
    public ResponseData query(@RequestBody InsertParams insertParams) {
        return new SuccessResponseData(influxService.queryAll(insertParams));
    }
    /**
     * description: 时序数据库按天查询当前电压总和测试
     * date: 2022/1/16 23:00
     * author: zhouhong
     * @param  * @param null
     * @return
     */
    @PostMapping("/influxdb/queryByOneDay")
    public ResponseData queryByOneDay(@RequestBody InsertParams insertParams) {
        return new SuccessResponseData(influxService.querySumByOneDay(insertParams));
    }
}
