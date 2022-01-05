package com.learn.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaobin
 * @date 2021/12/31 5:11 下午
 * @desc
 */
public class Jvmtest {

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<>();
        Integer i = 0;
        while (true) {
            Thread.sleep(1);
            list.add(i++);
        }
    }
}
