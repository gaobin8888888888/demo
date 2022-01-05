package com.learn.demo.test.abst;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaobin
 * @date 2021/7/6 1:48 下午
 * @desc
 */
public abstract class AbstractDemo {

    private static Map<String, AbstractDemo> map = new ConcurrentHashMap<>();

    public void register(String name) {
        map.put(name, this);
    }

    public static AbstractDemo getObject(String name) {
        return map.get(name);
    }

    public abstract void handle();
}
