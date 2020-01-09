package com.liuff.logger.framework;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liufengfang on 2020/1/3.
 */
@Component
public class SimpleLogDemo {
    public static Log LOGGER = LogFactory.getLog(SimpleLogDemo.class);

    public void druidLog() {
        LOGGER.debug(String.format("this is %s and %s",1,2));
    }

}
