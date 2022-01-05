package com.learn.demo.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockABCTest {

    private static Lock lock = new ReentrantLock();

    private static final int n = 3;

    private static int total = 0;

    public static void main1(String[] args) {
       new Thread(() ->{
           printABC("A", 0);
       }).start();

        new Thread(() ->{
            printABC("B", 1);
        }).start();

        new Thread(() ->{
            printABC("C", 2);
        }).start();
    }

    public static void printABC(String name, int index){
        try {
            for (int i = 0; i < n;){
                lock.lock();
                if (total % 3 == index){
                    i++;
                    total++;
                    System.out.println(name);
                }
                lock.unlock();
            }
        } catch (Exception e){
            System.out.println("name:"+name + ",  error:" + e);
        }
    }

    public static void main2(String[] args) {
        new Thread(() ->{
            printABC2("A", 0);
        }).start();

        new Thread(() ->{
            printABC2("B", 1);
        }).start();

        new Thread(() ->{
            printABC2("C", 2);
        }).start();
    }


    private final static Object object = new Object();

    public static void printABC2(String name, int index){
        try {
            for (int i = 0; i < n;){
                synchronized (object){
                    while (total % 3 != index){
                        object.wait();
                    }
                    total++;
                    i++;
                    System.out.println(name);
                    object.notifyAll();
                }
            }
        } catch (Exception e){
            System.out.println("name:"+name + ",  error:" + e);
        }
    }

    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    private static Condition condition3 = lock.newCondition();

    public static void main3(String[] args) {
        new Thread(() ->{
            printABC3("A", 0, condition1, condition2);
        }).start();

        new Thread(() ->{
            printABC3("B", 1, condition2, condition3);
        }).start();

        new Thread(() ->{
            printABC3("C", 2, condition3, condition1);
        }).start();
    }

    public static void printABC3(String name, int index, Condition now, Condition next){
        try {
            for (int i = 0; i < n;){
                lock.lock();
                while (total % 3 != index){
                    now.await();
                }
                total++;
                i++;
                System.out.println(name);
                next.signal();
                lock.unlock();
            }
        } catch (Exception e){
            System.out.println("name:"+name + ",  error:" + e);
        }
    }

    private static Semaphore sm1 = new Semaphore(1);
    private static Semaphore sm2 = new Semaphore(0);
    private static Semaphore sm3 = new Semaphore(0);

    public static void main4(String[] args) {
        new Thread(() ->{
            printABC4("A", 0, sm1, sm2);
        }).start();

        new Thread(() ->{
            printABC4("B", 1, sm2, sm3);
        }).start();

        new Thread(() ->{
            printABC4("C", 2, sm3, sm1);
        }).start();
    }

    public static void printABC4(String name, int index, Semaphore now, Semaphore next){
        try {
            for (int i = 0; i < n;){
                while (total % 3 != index){
                    now.acquire();
                }
                total++;
                i++;
                System.out.println(name);
                next.release();
            }
        } catch (Exception e){
            System.out.println("name:"+name + ",  error:" + e);
        }
    }

    static Thread t1 = null, t2 = null, t3 = null;

    public static void main(String[] args) {
        t1 = new Thread(() ->{
            for (int i = 0; i < n; i++){
                System.out.println("A");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() ->{
            for (int i = 0; i < n; i++){
                LockSupport.park();
                System.out.println("B");
                LockSupport.unpark(t3);
            }
        });

       t3 = new Thread(() ->{
           for (int i = 0; i < n; i++){
               LockSupport.park();
               System.out.println("C");
               LockSupport.unpark(t1);
           }
        });
       t1.start();
       t2.start();
       t3.start();
    }

    public static void printABC5(String name, int index, Thread next){
        try {
            for (int i = 0; i < n;){
                LockSupport.park();
                System.out.println(name);
                LockSupport.unpark(next);
            }
        } catch (Exception e){
            System.out.println("name:"+name + ",  error:" + e);
        }
    }
}
