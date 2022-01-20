package com.example.influxdb.influx.service;

import com.example.influxdb.influx.model.param.InsertParams;

import java.time.LocalDateTime;

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
      * description: InfluxDB查询全部
      * date: 2022/1/17 0:15
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    Object queryAll(InsertParams insertParams);
    /**
      * description: 时序数据库按天查询当前电压总和测试
      * date: 2022/1/17 1:01
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    Object querySumByOneDay(InsertParams insertParams);

}
