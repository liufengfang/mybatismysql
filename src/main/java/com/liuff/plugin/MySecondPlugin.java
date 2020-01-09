package com.liuff.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by liufengfang on 2019/12/27.
 */
@Intercepts({@Signature(type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})})
public class MySecondPlugin implements Interceptor {
    private static Log LOGGER = LogFactory.getLog(MySecondPlugin.class);

    private static String TEMP_TABLE_BY_PLUGIN = "TEMP_TABLE_BY_PLUGIN";

    private int limitNum;
    private String dbType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("1");
        //取出被拦截对象
        StatementHandler stmtHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStmtHandler = SystemMetaObject.forObject(stmtHandler);
        LOGGER.debug("metaStmtHandler.getGetterNames()="
                + Arrays.toString(metaStmtHandler.getGetterNames()));
        //获取完整的被代理对象
        while (metaStmtHandler.hasGetter("h")) {
            Object o = metaStmtHandler.getValue("h");
            metaStmtHandler = SystemMetaObject.forObject(o);
        }

        while (metaStmtHandler.hasGetter("target")) {
            Object o = metaStmtHandler.getValue("target");
            metaStmtHandler = SystemMetaObject.forObject(o);
        }

        String sql = (String) metaStmtHandler.getValue("delegate.boundSql.sql");
        sql = sql.trim();
        LOGGER.error("boudsql = " + sql);

        if ("mysql".equals(this.dbType) && sql.indexOf(TEMP_TABLE_BY_PLUGIN) == -1
                && sql.indexOf("select") == 0) {
            String newSql = "select * from (" + sql + ") " + TEMP_TABLE_BY_PLUGIN
                    + " limit " + limitNum;
            metaStmtHandler.setValue("delegate.boundSql.sql", newSql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.err.println("2");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.err.println("3");
        this.dbType = properties.getProperty("dbType");
        this.limitNum = Integer.valueOf(properties.getProperty("limitNum"));
    }
}
