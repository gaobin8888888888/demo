package com.learn.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learn.demo.test.abst.AbstractDemo;
import com.learn.demo.test.produce.EventProducer;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/test")
public class TestCon {

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${test.test:111}")
    private String testStr;


    @GetMapping("/test")
    public void test() {
//        eventProducer.publish("hello");
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methods = mapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methods.entrySet()) {
            RequestMappingInfo key = entry.getKey();
            HandlerMethod value = entry.getValue();
            PatternsRequestCondition patternsRequestCondition = key.getPatternsCondition();
            for (String url : patternsRequestCondition.getPatterns()) {
                System.out.println(url);
            }
        }
        System.out.println("key");
    }

    @GetMapping("/test1")
    public void test1(@RequestParam String s) throws NoHandlerFoundException {

        System.out.println("old:" + testStr);
        testStr = s;
        System.out.println("new:" + testStr);
//        int i = 0;
//        while (true) {
//            eventProducer.publish("hello" + i);
//
//            eventProducer.publish("world" + i);
//            i++;
//        }L a
    }

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(System.getProperty("java.class.path"));
        CompletableFuture.runAsync(() ->{
            System.out.println(11);
        });
    }
}
