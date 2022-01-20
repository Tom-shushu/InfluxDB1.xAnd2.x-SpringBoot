package com.example.influxdb.influx.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import lombok.Data;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
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
public class InfluxDBConfig {
    private String url;
    private String username;
    private String password;
    /**
      * description: 用于查询
      * date: 2022/1/20 23:11
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    @Bean(destroyMethod = "close")
    public InfluxDB influxDBClient(){
        return InfluxDBFactory.connect(this.url, this.username, this.password);
    }
    /**
      * description: 用于写入
      * date: 2022/1/20 23:12
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    @Bean(name = "influxDbWriteApi",destroyMethod = "close")
    public WriteApi influxDbWriteApi(){
        InfluxDBClient influxDBClient = InfluxDBClientFactory.createV1(this.url, this.username,
                this.password.toCharArray(), "influx_test", "autogen");
        return influxDBClient.getWriteApi();
    }
}
