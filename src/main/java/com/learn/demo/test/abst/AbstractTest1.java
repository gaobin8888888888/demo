package com.learn.demo.test.abst;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author gaobin
 * @date 2021/7/6 1:50 下午
 * @desc
 */
@Component
public class AbstractTest1 extends AbstractDemo implements InitializingBean {

    @Override
    public void handle() {
        System.out.println("test1");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.register("test1");
    }
}
