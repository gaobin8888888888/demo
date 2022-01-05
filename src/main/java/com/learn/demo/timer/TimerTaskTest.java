package com.learn.demo.timer;

import java.util.Timer;

/**
 * @author gaobin
 * @date 2021/12/23 6:02 下午
 * @desc
 */
public class TimerTaskTest {

    public static void main(String[] args) {
        /**
         * 延迟多久执行任务
         */
        new Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println(111);
            }
        }, 6000);
    }
}
