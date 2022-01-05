//package com.learn.demo.test.aop;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController
//
///**
// * @author gb
// * @date 2021/3/15 6:02 下午
// * @description
// */
//@RestController
//@RequestMapping("/aop")
//public class TestController {
//
//    @Autowired
//    private PrototypeService prototypeService;
//
//    @RequestMapping("/test")
//    @OnMetrics(name = "/aop/test")
//    public void test(){
//        prototypeService.print("test");
//    }
//}
