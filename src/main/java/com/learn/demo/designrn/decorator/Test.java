package com.learn.demo.designrn.decorator;

/**
 * @author gaobin
 * @date 2021/9/15 11:21 上午
 * @desc 装饰者模式
 */
public class Test {

    public static void main(String[] args) {
        Water water = new RedWater();
        System.out.println(water.getDesc() + "        " + water.cost());

        Water water1 = new RedWater();
        water1 = new Tea1(water1);
        water1 = new Tea1(water1);
        water1 = new Tea2(water1);
        System.out.println(water1.getDesc() + "        " + water1.cost());

        Water water2 = new GreenWater();
        water2 = new Tea1(water2);
        water2 = new Tea1(water2);
        water2 = new Tea2(water2);
        System.out.println(water2.getDesc() + "        " + water2.cost());
    }
}
