package com.learn.demo.lambda;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author gaobin
 * @date 2021/12/23 6:21 下午
 * @desc
 */
public class FunctionInterfaceTest {

    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x == 5;
        Predicate<Integer> predicate1 = x -> x == 2;
        System.out.println(predicate.test(5));

        Predicate<Integer> s = predicate.or(predicate1);
        System.out.println(s.test(2));
        System.out.println(s.test(5));
        System.out.println(s.test(3));

        BinaryOperator<Long> binaryOperator = (x, y) -> x + y;
        Long aLong = binaryOperator.apply(2L, 4L);
        System.out.println(aLong);

        BiFunction<Integer, Integer, String> biFunction = (x, y) -> x + " " + y;
        System.out.println(biFunction.apply(1, 3));

        Supplier<ThreadLocal<Integer>> threadLocalSupplier = () -> new ThreadLocal<>();
        ThreadLocal<Integer> integerThreadLocal1 = threadLocalSupplier.get();
        ThreadLocal<Integer> integerThreadLocal2 = threadLocalSupplier.get();
        System.out.println(integerThreadLocal1);
        System.out.println(integerThreadLocal2);

    }
}
