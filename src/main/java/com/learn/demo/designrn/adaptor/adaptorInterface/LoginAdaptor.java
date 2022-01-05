package com.learn.demo.designrn.adaptor.adaptorInterface;

/**
 * @author gaobin
 * @date 2021/9/16 7:52 下午
 * @desc
 */
public class LoginAdaptor extends LoginServiceImpl {

    @Override
    public String loginWeChat(String openId) {
        return "ok";
    }

    public static void main(String[] args) {

    }
}
