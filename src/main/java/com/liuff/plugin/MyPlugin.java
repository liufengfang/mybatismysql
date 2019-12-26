package com.liuff.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * Created by liufengfang on 2019/12/26.
 */
@Intercepts({@Signature(type = Executor.class, //确定要拦截的对象
        method = "update", //确定要拦截的方法
        args = {MappedStatement.class, Object.class})}) //拦截方法的参数列表
public class MyPlugin implements Interceptor {
    Properties props = null;

    /**
     * 代替拦截对象方法的内容
     *
     * @param invocation 责任链对象
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("befor...");
        //如果当前代理是一个非代理对象，那么它就回调真是拦截对象的方法，
        // 如果是代理对象,它会调度下个插件代理对象的invoke方法
        Object obj = invocation.proceed();
        System.err.println("after...");
        return obj;
    }

    /**
     * 生成对象的代理，这里常用mybatis提供的Plugin类的wrap方法
     *
     * @param target 被代理的对象
     */
    @Override
    public Object plugin(Object target) {
        System.err.println("调用生成代理对象...");
        return Plugin.wrap(target, this);
    }

    /**
     * 获取插件配置的属性,我们在Mybatis的配置文件里面去配置
     *
     * @param properties 是Mybatis配置的参数
     */
    @Override
    public void setProperties(Properties properties) {
        System.err.println("MyPlugin.setProperties(),dbType="
                + properties.getProperty("dbType"));
        this.props = properties;
    }
}
