package com.learn.demo.lock;

/**
 * @author gaobin
 * @date 2021/8/17 4:36 下午
 * @desc
 */
public class InterruptTestThread extends Thread {

    int i = 0;

    @Override
    public void run() {
        try {
            System.out.println("name:" + Thread.currentThread().getName());
            System.out.println(Thread.interrupted());
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isInterrupted());
            while (!Thread.interrupted()) {
                System.out.println("time：" + System.currentTimeMillis() + "    线程进行第 " + i + " 次工作,此时 inerrupt 标志位为："+Thread.currentThread().isInterrupted());
                Thread.sleep(100);
                i++;
            }

            //这里可以进行善后工作，比如释放资源
            System.out.println("time：" + System.currentTimeMillis() + "    线程接收到 interrupt 信号，离开工作区.此时 inerrupt 标志位为："+Thread.currentThread().isInterrupted());
        } catch (InterruptedException e) {
            //这里可以进行善后工作，比如释放资源
            System.out.println("time：" + System.currentTimeMillis() + "    线程在 sleep 的过程中被 interrupted，此时 interrupt 标志位为："+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptTestThread thread = new InterruptTestThread();
        thread.start();
        System.out.println("thread name:" + thread.getName());
        System.out.println("Thread.currentThread name:" + Thread.currentThread().getName());
        Thread.sleep(1000);
        thread.interrupt();
    }
}
