package com.learn.demo.tanma;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gaobin
 * @date 2021/11/9 9:19 下午
 * @desc
 */
public class Test2 {

    public static void main(String[] args) {
        Department d1 = new Department(1L, "学校");
        Department d2 = new Department(2L, "一年级");
        Department d3 = new Department(3L, "二年级");
        Department d4 = new Department(4L, "三年级");
        Department d5 = new Department(5L, "语文");
        Department d6 = new Department(6L, "数学");
        Department d7 = new Department(7L, "语文");
        Department d8 = new Department(8L, "英语");
        Department d9 = new Department(9L, "数学一组");

        List<Department> list = new ArrayList<>();
        list.add(d9);
        d6.setChildren(list);

        list = new ArrayList<>();
        list.add(d5);
        list.add(d6);
        d2.setChildren(list);

        list = new ArrayList<>();
        list.add(d7);
        d3.setChildren(list);

        list = new ArrayList<>();
        list.add(d8);
        d4.setChildren(list);

        list = new ArrayList<>();
        list.add(d2);
        list.add(d3);
        list.add(d4);
        d1.setChildren(list);

        String selectName = "数学";
        List<Long> result = new ArrayList<>();
        if (Objects.equals(d1.getName(), selectName)) {
            result.add(d1.getId());
        }
        select(d1.getChildren(), result, selectName);
        System.out.println(result);
    }

    private static void select(List<Department> departments, List<Long> result, String selectName) {
        if (departments == null || departments.size() == 0) {
            return;
        }
        for (Department department : departments) {
            if (Objects.equals(department.getName(), selectName)) {
                result.add(department.getId());
            }
            select(department.getChildren(), result, selectName);
        }
    }
}
