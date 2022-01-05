package com.learn.demo.designrn.adaptor.adaptorObj;

import com.learn.demo.designrn.adaptor.LoginService;
import com.learn.demo.designrn.adaptor.LoginServiceImpl;
import com.learn.demo.designrn.adaptor.WechatLoginService;

/**
 * @author gaobin
 * @date 2021/9/16 9:48 上午
 * @desc
 */
public class LoginForWeChatAdaptor implements WechatLoginService {

    private LoginService loginService;

    public LoginForWeChatAdaptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public String loginWeChat(String openId) {
        return loginService.login(openId, null);
    }

    public static void main(String[] args) {
        LoginService loginService = new LoginServiceImpl();
        LoginForWeChatAdaptor adaptor = new LoginForWeChatAdaptor(loginService);
        System.out.println(adaptor.loginWeChat("111"));
    }
}
