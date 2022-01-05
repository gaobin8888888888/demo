package com.learn.demo.designrn.strategy;

/**
 * @author gaobin
 * @date 2021/9/16 8:52 下午
 * @desc
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void deal() {
        strategy.dealData();
    }

    public static void main(String[] args) {
        Strategy strategy1 = new Strategy1();
        Context context = new Context(strategy1);
        context.deal();

        Strategy strategy2 = new Strategy2();
        context = new Context(strategy2);
        context.deal();
    }
}
