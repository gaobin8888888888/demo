package com.learn.demo.designrn.observer;

/**
 * @author gaobin
 * @date 2021/9/16 8:27 下午
 * @desc
 */
public class CreateSubway extends Subject {

    public void doSomeThings(){
        System.out.println("subjectdosomething");
        super.notifyObserver();
    }
}
