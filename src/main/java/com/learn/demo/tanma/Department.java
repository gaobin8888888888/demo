package com.learn.demo.tanma;

import java.util.List;

/**
 * @author gaobin
 * @date 2021/11/9 9:22 下午
 * @desc
 */
public class Department {

    private Long id;
    private String name;
    private List<Department> children;

    Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
