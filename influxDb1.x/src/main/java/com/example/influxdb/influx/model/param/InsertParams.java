package com.example.influxdb.influx.model.param;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;

import java.time.Instant;

/**
 * description: 时序数据库添加参数
 * date: 2022/1/16 20:40
 * author: zhouhong
 */
@Data
@Measurement(name = "influx_test")
public class InsertParams {
    /**
      * 用电量
      */
    @Column
    private Double energyUsed;

    /**
     * 有功功率
     */
    @Column(tag = true)
    private Double power;

    /**
     * 电流
     */
    @Column
    private Double current;
    /**
     * 电压
     */
    @Column
    private Double voltage;
    /**
     * 时间戳
     */
    @Column(timestamp = true)
    private Instant time;
}
