package com.learn.demo.designrn.adaptor.adaptorInterface;

/**
 * @author gaobin
 * @date 2021/9/16 7:50 下午
 * @desc
 */
public abstract class LoginServiceImpl implements LoginService {

    @Override
    public String login(String username, String password) {
        return "login ok";
    }

    @Override
    public String loginWeChat(String openId) {
        return "wechat ok";
    }
}
