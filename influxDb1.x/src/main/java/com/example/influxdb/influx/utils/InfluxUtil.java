package com.example.influxdb.influx.utils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Component;

@Component
public class InfluxUtil {

    /**
      * description: 通用查询
      * date: 2022/1/20 23:13
      * author: zhouhong
      * @param  * @param null
      * @return
      */
    public QueryResult query(String command, String database, InfluxDB influxDB) {
        Query query = new Query(command, database);
        return influxDB.query(query);
    }
}
