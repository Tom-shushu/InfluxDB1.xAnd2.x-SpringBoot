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
 * description:
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
    @PostMapping("/inlfuxdb/insert")
    public ResponseData insert(@RequestBody InsertParams insertParams) {
        influxService.insert(insertParams);
        return new SuccessResponseData();
    }


    @PostMapping("/inlfuxdb/queue")
    public ResponseData queue(@RequestBody InsertParams insertParams) {
        return new SuccessResponseData(influxService.queue());
    }

}
