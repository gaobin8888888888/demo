package com.learn.demo.tanma;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        list.add("123abccd");
        list.add("abcc123");
        for (String s : list) {
            char[] ch = s.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : ch) {
                if (c >= '0' && c <= '9'){
                    stringBuilder.append(c);
                } else if (stringBuilder.length() > 0){
                    break;
                }
            }
            map.put(stringBuilder.toString(), s);
        }
        list = new ArrayList<>();
        list.addAll(map.values());
        System.out.println(list);
    }
}
