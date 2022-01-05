package com.learn.demo.scheduler;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public abstract class TestSchedulerTest implements InitializingBean, Runnable, DisposableBean {

    Thread thread;

    boolean flag = true;

    @Override
    public void run() {
        System.out.println(getClass().getName());
        while (flag){
            System.out.println(Thread.currentThread().getName() + " test");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            thread = new Thread(this);
            thread.start();
        } catch (Exception e){
            System.out.println("error");
        }
    }

    @Override
    public void destroy() throws Exception {
        flag = false;
    }
}
