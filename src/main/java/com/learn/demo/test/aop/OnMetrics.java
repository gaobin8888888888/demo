package com.learn.demo.test.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gb
 * @date 2021/3/15 6:04 下午
 * @description
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnMetrics {

    String name() default "";

    boolean count() default true;

    boolean timer() default true;
}
