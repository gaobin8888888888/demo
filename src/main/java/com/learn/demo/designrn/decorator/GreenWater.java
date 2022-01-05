package com.learn.demo.designrn.decorator;

/**
 * @author gaobin
 * @date 2021/9/15 11:16 上午
 * @desc
 */
public class GreenWater extends Water {

    public GreenWater() {
        this.desc = "green";
    }

    @Override
    public int cost() {
        return 2;
    }
}
