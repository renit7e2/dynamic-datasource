package com.cn.dynamic.ds.core.annotation;

import com.cn.dynamic.ds.common.exception.DynamicDsException;
import com.cn.dynamic.ds.core.DsParam;
import com.cn.dynamic.ds.core.algorithm.DsRoutingAlgorithm;
import com.cn.dynamic.ds.core.holder.DataSourceLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 数据源切面
 * <pre>Created by Kaizen Xue on 2017/12/27.</pre>
 */
@Aspect
@Order(Integer.MIN_VALUE)
@Slf4j
public class DynamicDSAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 数据源切点
     */
    @Pointcut("(@within(com.cn.dynamic.ds.core.annotation.DynamicDS)) || (@annotation(com.cn.dynamic.ds.core.annotation.DynamicDS)) ")
    public void dataSourcePoint() {
    }

    /**
     * 环绕织入
     */
    @Around("dataSourcePoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            DataSourceLocal.clear();
            return process(joinPoint);
        } catch (Throwable t) {
            logger.error("dynamic dataSource error", t);
            DataSourceLocal.clear();
            throw new DynamicDsException(t);
        } finally {
            DataSourceLocal.clear();
        }
    }


    private Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method proxyMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DynamicDS dynamicDS = null;
        if (joinPoint.getSignature().getDeclaringType().isAnnotationPresent(DynamicDS.class)) {
            dynamicDS = (DynamicDS) joinPoint.getSignature().getDeclaringType().getAnnotation(DynamicDS.class);
        }
        if (proxyMethod.isAnnotationPresent(DynamicDS.class)) {
            dynamicDS = proxyMethod.getAnnotation(DynamicDS.class);
        }
        if (dynamicDS == null || dynamicDS.value() == null) {
            return joinPoint.proceed();
        }
        DsParam dsParam = getDsParam(joinPoint);
        if (dsParam == null) {
            return joinPoint.proceed();
        }
        DsRoutingAlgorithm dsRoutingAlgorithm = dynamicDS.value().newInstance();

        DataSourceLocal.setDataSource(dsRoutingAlgorithm.routing(dsParam));
        return joinPoint.proceed();
    }

    private DsParam getDsParam(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null && args.length == 0) {
            return null;
        }
        for (Object arg : args) {
            if (arg instanceof DsParam) {
                return (DsParam) arg;
            }
        }
        return null;
    }
}
