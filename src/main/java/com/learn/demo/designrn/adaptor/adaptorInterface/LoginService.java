package com.learn.demo.designrn.adaptor.adaptorInterface;

/**
 * @author gaobin
 * @date 2021/9/16 7:50 下午
 * @desc
 */
public interface LoginService {

    String login(String username, String password);

    String loginWeChat(String openId);
}
