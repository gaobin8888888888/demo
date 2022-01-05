package com.learn.demo.lock;

/**
 * @author gaobin
 * @date 2021/12/22 2:17 下午
 * @desc 三条线程依次交替输出十次ABC
 */
public class ABCTest {

    private volatile static int flag = 0;

    public static void main(String[] args) {
        ABCTest abcTest = new ABCTest();
        new Thread(() ->{
            abcTest.print("A", 0);
        }, "A").start();
        new Thread(() ->{
            abcTest.print("B", 1);
        }, "B").start();
        new Thread(() ->{
            abcTest.print("C", 2);
        }, "C").start();
    }

    public synchronized void print(String s, int index) {
        while (true) {
            try {
                while (flag % 3 != index) {
                    this.wait();
                }
                if (flag >= 30) {
                    return;
                }
                System.out.println(Thread.currentThread().getId());
                System.out.println(Thread.currentThread().getName() + " " + s);
                flag++;
                this.notifyAll();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
