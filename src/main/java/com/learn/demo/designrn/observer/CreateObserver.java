package com.learn.demo.designrn.observer;

/**
 * @author gaobin
 * @date 2021/9/16 8:28 下午
 * @desc
 */
public class CreateObserver implements Observer {
    @Override
    public void update() {
        System.out.println("arrived msg to deal");
    }
}
