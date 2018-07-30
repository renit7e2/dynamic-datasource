package com.cn.dynamic.ds.core;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <pre>Created by Kaizen Xue on 2018/7/27.</pre>
 */
@Data
public class DsParam<T, S> {
    /**
     * 数据库路由参数
     */
    private T param;

    /**
     * 扩展参数
     */
    private S extendParam;

    public DsParam buildParam(T t) {
        this.param = t;
        return this;
    }

    public DsParam buildExtendParam(S s) {
        this.extendParam = s;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DsParam)) return false;
        if (!super.equals(o)) return false;

        DsParam<?, ?> dsParam = (DsParam<?, ?>) o;

        return param != null ? param.equals(dsParam.param) : dsParam.param == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (param != null ? param.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
