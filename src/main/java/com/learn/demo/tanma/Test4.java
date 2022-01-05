package com.learn.demo.tanma;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.comparator.Comparators;

import java.util.*;

/**
 * @author gaobin
 * @date 2021/11/9 9:54 下午
 * @desc
 */
public class Test4 {

    int[][] array = new int[1000][10];

    public static void main1(String[] args) {

    }

    private static void select(int[][] array) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (map.size() < 10) {
                    if (map.containsKey(array[i][j])) {
                        map.put(array[i][j], map.get(array[i][j]) + 1);
                    } else {
                        map.put(array[i][j], 1);
                    }
                } else {
                    Integer x = map.keySet().toArray(new Integer[0])[0];
                    if (x < array[i][j]) {
                        if (map.get(x) > 1) {
                            map.put(x, map.get(x) - 1);
                        } else {
                            map.remove(x);
                        }
                        map.put(array[i][j], 1);
                    }
                }
            }
        }
    }
}
