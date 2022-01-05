package com.learn.demo.math;

import java.util.Objects;

/**
 * @author gaobin
 * @date 2021/9/24 4:06 下午
 * @desc
 */
public class Heap {

    public static void main(String[] args) {
        System.out.println(transfer("2147483648"));
    }



    private static int transfer(String str) {
        if (Objects.isNull(str) || str.length() <= 0) {
            throw new NullPointerException();
        }
        int len = str.length();
        boolean flag = false;
        char firstChar = str.charAt(0);
        int i = 0;
        if (firstChar < '0') {
            if (firstChar == '-') {
                flag = true;
            } else if (firstChar != '+'){
                throw new NumberFormatException();
            }
            if (len == 1){
                throw new NumberFormatException();
            }
            i++;
        }
        int result = 0;
        while (i < len) {
            int d = Character.digit(str.charAt(i++), 10);
            if (d < 0) {
                throw new NumberFormatException();
            }
            result = result * 10 + d;
            System.out.println(result);
        }
        return flag ? -1 * result : result;
    }
}
