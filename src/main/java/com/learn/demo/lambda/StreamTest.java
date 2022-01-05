package com.learn.demo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author gaobin
 * @date 2021/12/23 8:16 下午
 * @desc
 */
public class StreamTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(111);

        // 惰性求值
        Stream<Integer> stringStream = list.stream().filter(s -> Objects.equals(s, 111));

        // 及早求值
        long count = list.stream().filter(s -> {
            System.out.println("2:" + s);
            return Objects.equals(s, "111");
        }).count();

        list.add(11);
        list.add(1);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

    }
}
