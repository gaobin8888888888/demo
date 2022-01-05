package com.learn.demo.lock;

/**
 * @author gaobin
 * @date 2021/8/10 10:23 下午
 * @desc 可重入锁
 */
public class ReEntryLock {

    private boolean lockFlag = false;
    private Thread thread = null;
    private int lockCount = 0;

    private synchronized void lock() throws InterruptedException {
        if (lockFlag && Thread.currentThread() != thread) {
            wait();
        }
        lockFlag = true;
        lockCount++;
        thread = Thread.currentThread();
    }

    private synchronized void unlock() throws InterruptedException {
        if (Thread.currentThread() == thread) {
            lockCount--;
            if (lockCount == 0) {
                lockFlag = false;
                thread = null;
                notify();
            }
        }
    }

    public void doSomething() throws InterruptedException {
        lock();
        System.out.println(111);
        unlock();
    }

    public static void main(String[] args) throws Exception {
        ReEntryLock entryLock = new ReEntryLock();
        entryLock.lock();
        entryLock.doSomething();
        entryLock.unlock();
    }
}
