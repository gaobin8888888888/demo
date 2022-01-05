package com.learn.demo.io.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class TestMain {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        KeyModel key = new KeyModel();
        key.setAge(22);
        key.setName("test");
        File file = new File("/Users/gaobin/work/data/test.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(key);
        objectOutputStream.close();

        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        KeyModel model = (KeyModel) ois.readObject();
        System.out.println(model);
        ois.close();

    }

}
