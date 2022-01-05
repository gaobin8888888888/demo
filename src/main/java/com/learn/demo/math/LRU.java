package com.learn.demo.math;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gaobin
 * @date 2021/8/17 3:57 下午
 * @desc
 */
public class LRU<K, V> extends LinkedHashMap<K, V> {

    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public LRU(int maxCapacity){
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new LRU<>(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
    }
}
