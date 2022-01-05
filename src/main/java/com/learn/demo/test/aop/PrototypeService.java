package com.learn.demo.test.aop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author gaobin
 * @date 2021/10/25 5:26 下午
 * @desc
 */
@Service
@Scope("prototype")
public class PrototypeService {

    public void print(String name) {
        System.out.println(name);
    }
}
