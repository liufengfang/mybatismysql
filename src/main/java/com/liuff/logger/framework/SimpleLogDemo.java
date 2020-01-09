package com.liuff.logger.framework;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liufengfang on 2020/1/3.
 */
@Component
public class SimpleLogDemo {
    public static Log LOGGER = LogFactory.getLog(SimpleLogDemo.class);
    public static Logger SLF4J_LOGGER = LoggerFactory.getLogger(SimpleLogDemo.class);

    public void druidLog() {
        LOGGER.debug(String.format("this is %s and %s",1,2));
    }

    public void slf4jLog() {
        SLF4J_LOGGER.debug("I'm slf4j debug,{} and {}", 3, 4);
        SLF4J_LOGGER.info("I'm slf4j info,{} and {}", 5, 6);
    }
}
