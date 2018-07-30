package com.cn.dynamic.ds.core.annotation;

import com.cn.dynamic.ds.core.algorithm.DefaultRoutingAlgorithm;
import com.cn.dynamic.ds.core.algorithm.DsRoutingAlgorithm;

import java.lang.annotation.*;

/**
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DynamicDS {
    Class<? extends DsRoutingAlgorithm> value() default DefaultRoutingAlgorithm.class;
}
