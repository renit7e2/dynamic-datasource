package com.cn.dynamic.ds.core.holder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.util.*;

/**
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceHolder {
    private static DataSourceHolder INSTANCE = new DataSourceHolder();

    private Map<String, DataSource> dataSources = new HashMap<>();


    public static DataSourceHolder getHolder() {
        return INSTANCE;
    }

    public void initDataSource(Map<String, DataSource> dataSources) {
        this.dataSources.putAll(dataSources);
    }


    public Set<String> getAllDataSourceName() {
        return this.dataSources.keySet();
    }

    public List<DataSource> getAllDataSource() {
        return new ArrayList<>(this.dataSources.values());
    }
}
