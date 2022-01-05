package com.learn.demo.test.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author gaobin
 * @date 2021/6/19 3:11 下午
 * @desc
 */
@Component
@EnableAsync
public class EventProducer {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String name){
        applicationEventPublisher.publishEvent(name);
    }

    @Async(value = "appActionEventThreadExecutor")
    @EventListener
    public void handle(String name){
        System.out.println("rev:" + name);
    }
}
