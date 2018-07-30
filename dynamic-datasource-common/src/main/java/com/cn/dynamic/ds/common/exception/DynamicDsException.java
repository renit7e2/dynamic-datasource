package com.cn.dynamic.ds.common.exception;

/**
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
public class DynamicDsException extends RuntimeException {
    public DynamicDsException(String message) {
        super(message);
    }

    public DynamicDsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DynamicDsException(Throwable cause) {
        super(cause);
    }
}
