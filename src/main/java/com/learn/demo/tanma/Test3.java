package com.learn.demo.tanma;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaobin
 * @date 2021/11/9 9:33 下午
 * @desc
 */
public class Test3 {

    private static volatile int state = 0;
    private static int time = 10;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                printABC('A', 0);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                printABC('B', 1);
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                printABC('C', 2);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printABC(char ch, int index) {
        for (int i = 0; i < time; ) {
            lock.lock();
            try {
                if (state % 3 == index) {
                    System.out.print(ch);
                    state++;
                    i++;
                }
            } catch (Exception e) {
                System.out.println("error");
            } finally {
                lock.unlock();
            }
        }
    }

}
