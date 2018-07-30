package com.cn.dynamic.ds.core.algorithm;

import com.cn.dynamic.ds.core.DsParam;

/**
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
public class DefaultRoutingAlgorithm implements DsRoutingAlgorithm {
    @Override
    public String routing(DsParam dsParam) {
        return "dataSource_" + dsParam.getParam().toString();
    }
}
