package com.learn.demo.designrn.adaptor.adaptorClass;

import com.learn.demo.designrn.adaptor.LoginServiceImpl;
import com.learn.demo.designrn.adaptor.WechatLoginService;

/**
 * @author gaobin
 * @date 2021/9/16 9:48 上午
 * @desc
 */
public class LoginForWeChatAdaptor extends LoginServiceImpl implements WechatLoginService {

    @Override
    public String loginWeChat(String openId) {
        return login(openId, null);
    }

    public static void main(String[] args) {
        LoginForWeChatAdaptor adaptor = new LoginForWeChatAdaptor();
        System.out.println(adaptor.loginWeChat("111"));
    }
}
