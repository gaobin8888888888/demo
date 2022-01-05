package com.learn.demo.designrn.decorator;

/**
 * @author gaobin
 * @date 2021/9/15 11:16 上午
 * @desc
 */
public class RedWater extends Water {

    public RedWater() {
        this.desc = "red";
    }

    @Override
    public int cost() {
        return 1;
    }
}
