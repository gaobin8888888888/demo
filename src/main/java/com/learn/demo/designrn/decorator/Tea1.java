package com.learn.demo.designrn.decorator;

/**
 * @author gaobin
 * @date 2021/9/15 11:20 上午
 * @desc
 */
public class Tea1 extends WaterAbs {

    private Water water;

    public Tea1(Water water) {
        this.water = water;
    }

    @Override
    public int cost() {
        return 3 + water.cost();
    }

    @Override
    public String getDesc() {
        return water.getDesc() + ",tea1";
    }
}
