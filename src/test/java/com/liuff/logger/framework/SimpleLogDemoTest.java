package com.liuff.logger.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liufengfang on 2020/1/9.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleLogDemoTest {
    @Autowired
    private SimpleLogDemo simpleLogDemo;

    @Test
    public void druidLogTest() {
        simpleLogDemo.druidLog();
    }

    @Test
    public void slf4jLogTest() {
        simpleLogDemo.slf4jLog();
    }

}
