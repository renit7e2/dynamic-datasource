package com.cn.dynamic.ds.core.algorithm;

import com.cn.dynamic.ds.core.DsParam;

/**
 * 数据源路由算法
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
public interface DsRoutingAlgorithm<T, S> {

    String routing(DsParam<T, S> dsParam);
}
