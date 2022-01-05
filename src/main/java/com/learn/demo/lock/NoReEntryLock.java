package com.learn.demo.lock;

/**
 * @author gaobin
 * @date 2021/8/10 10:23 下午
 * @desc 不可重入锁
 */
public class NoReEntryLock {

    private boolean lockFlag = false;

    private synchronized void lock() throws InterruptedException {
        if (lockFlag) {
            wait();
        }
        lockFlag = true;
    }

    private synchronized void unlock() throws InterruptedException {
        lockFlag = false;
        notify();
    }

    public void doSomething() throws InterruptedException {
        lock();
        System.out.println(111);
        unlock();
    }

    public static void main(String[] args) throws Exception {
        NoReEntryLock entryLock = new NoReEntryLock();
        entryLock.lock();
        entryLock.doSomething();
        entryLock.unlock();
    }
}
