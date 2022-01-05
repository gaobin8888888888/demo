package com.learn.demo.designrn.decorator;

/**
 * @author gaobin
 * @date 2021/9/15 11:14 上午
 * @desc
 */
public abstract class Water {

    public String desc;

    public String getDesc() {
        return desc;
    }

    public abstract int cost();
}
