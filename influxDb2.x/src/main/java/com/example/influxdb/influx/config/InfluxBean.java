package com.example.influxdb.influx.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxTable;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InfluxBean{
    /**
     * 数据库url地址
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
     * 数据库连接
     */
    private InfluxDBClient client;
    /**
     * 构造方法
     */
    public InfluxBean(String influxUrl, String bucket, String org, String token) {
        this.influxUrl = influxUrl;
        this.bucket = bucket;
        this.org = org;
        this.token = token;
        this.client = getClient();
    }
    /**
     * 获取连接
     */
    private InfluxDBClient getClient() {
        if (client == null) {
            client  = InfluxDBClientFactory.create(influxUrl, token.toCharArray());
        }
        return client;
    }
    /**
     * 写入数据(以秒为时间单位)
     */
    public void write(Object object){
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeMeasurement(bucket, org, WritePrecision.NS, object);
        }
    }

    /**
     * 读取数据
     */
    public List<FluxTable> queryTable(String fluxQuery){
        return client.getQueryApi().query(fluxQuery, org);
    }
}