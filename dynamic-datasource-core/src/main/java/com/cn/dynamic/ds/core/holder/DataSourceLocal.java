package com.cn.dynamic.ds.core.holder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <pre>Created by Kaizen Xue on 2018/7/30.</pre>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceLocal {
    private static ThreadLocal<String> dataSourceThreadLocal = new ThreadLocal<>();

    public static String get() {
        return dataSourceThreadLocal.get();
    }

    public static void setDataSource(String dataSource) {
        dataSourceThreadLocal.set(dataSource);
    }

    public static void remove() {
        dataSourceThreadLocal.remove();
    }

    public static void clear() {
        remove();
    }
}
