package com.example.influxdb.influx.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import lombok.Data;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
  * description: influxDb 连接配置
  * date: 2022/1/16 20:35
  * author: zhouhong
  */
@Data
@Configuration
@ConfigurationProperties(prefix = "influx")
public class InfluxConfig {
    /**
     * url地址
     */
    private String influxUrl;
    /**
     * 桶(表)
     */
    private String bucket;
    /**
     * 组织
     */
    private String org;
    /**
     * token
     */
    private String token;
    /**
     * 初始化bean
     */
    @Bean(name = "influx")
    public InfluxBean InfluxBean() {
        return new InfluxBean(influxUrl, bucket, org, token);
    }
}