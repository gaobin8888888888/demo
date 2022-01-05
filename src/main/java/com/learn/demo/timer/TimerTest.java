package com.learn.demo.timer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author gb
 * @date 2021/3/16 3:57 下午
 * @description
 */
@Service
public class TimerTest implements ApplicationRunner {

    @Autowired
    private HashedWheelTimer timer;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("start....");
        timer.newTimeout(new TimerTask() {
            @Override  
            public void run(Timeout timeout) throws Exception {
                System.out.println(111 + " " + new Date());
                timer.newTimeout(this, 1, TimeUnit.MINUTES);
                System.out.println(222 + " " + new Date());
            }
        }, 10, TimeUnit.SECONDS);

        timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println(333 + " " + new Date());
                timer.newTimeout(this, 1, TimeUnit.MINUTES);
                System.out.println(444 + " " + new Date());
            }
        }, 10, TimeUnit.SECONDS);
        System.out.println("end....");
    }
}
