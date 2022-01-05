package com.learn.demo.designrn.observer;

/**
 * @author gaobin
 * @date 2021/9/16 8:29 下午
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        CreateSubway subject = new CreateSubway();

        Observer observer = new CreateObserver();
        Observer observer2 = new CreateObserver();
        subject.add(observer);
        subject.add(observer2);
        subject.doSomeThings();
    }
}
