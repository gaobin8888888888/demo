package com.learn.demo.io.io;

import lombok.Data;
import java.io.Serializable;

@Data
public class KeyModel implements Serializable {

    private transient Integer age;

    private String name;

}
