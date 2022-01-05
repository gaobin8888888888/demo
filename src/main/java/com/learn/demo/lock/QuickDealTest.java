package com.learn.demo.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaobin
 * @date 2021/9/13 9:10 上午
 * @desc 获取三个线程中处理最快的结果
 */
public class QuickDealTest {

    private static volatile int state = 0;

    public static void main(String[] args) {
        System.out.println(getFast());
    }

    public static List getFast() {

        final List[] result = new List[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (state == 0) {
                    result[0] = a();
                    state = state + 1;
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (state == 0) {
                    result[0] = b();
                    state = state + 1;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (state == 0) {
                    result[0] = c();
                    state = state + 1;
                }
            }
        }).start();

        return result[0];
    }

    public static List a(){
        List<String> strings = new ArrayList<>();
        strings.add("1");
        return strings;
    }
    public static List b(){
        List<String> strings = new ArrayList<>();
        strings.add("2");
        return strings;
    }
    public static List c(){
        List<String> strings = new ArrayList<>();
        strings.add("3");
        return strings;
    }

}
