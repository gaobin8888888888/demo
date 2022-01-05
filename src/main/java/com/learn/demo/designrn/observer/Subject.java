package com.learn.demo.designrn.observer;

import java.util.Vector;

/**
 * @author gaobin
 * @date 2021/9/16 8:19 下午
 * @desc
 */
public class Subject {

    private Vector<Observer> vector = new Vector<>();

    public void add(Observer str) {
        vector.add(str);
    }

    public void delete(Observer str) {
        vector.remove(str);
    }

    public void notifyObserver() {
        for (Observer s : vector) {
            s.update();
        }
    }
}
