package com.cn.dynamic.ds.core.datasource;

import com.cn.dynamic.ds.common.util.ReflectUtil;
import com.cn.dynamic.ds.core.holder.DataSourceHolder;
import com.cn.dynamic.ds.core.holder.DataSourceLocal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * <pre>Created by Kaizen Xue on 2018/7/30.</pre>
 */
public class DynamicDataSource extends AbstractRoutingDataSource implements InitializingBean {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceLocal.get();
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        Object sources = ReflectUtil.getFieldValue(this, "targetDataSources");
        HashMap<String, DataSource> dataSourceMap = (HashMap<String, DataSource>) sources;
        DataSourceHolder.getHolder().initDataSource(dataSourceMap);
    }
}
