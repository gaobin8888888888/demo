package com.learn.demo.classLoad;

import java.io.FileInputStream;

/**
 * @author gaobin
 * @date 2021/12/31 11:35 上午
 * @desc 自定义类加载器实现
 */
public class MyCustomClassLoaderTest {

    static String classPath = "/Users/gaobin/work/github/demo/target/classes";
    static String name = "MyCustomClassLoaderTest";

    public static void main(String[] args) throws Exception {
        // 初始化自定义加载器，会先初始化父类ClassLoader，其中会把自定义加载器的父加载器设置为应用程序加载器
        MyCustomClassLoader classLoader = new MyCustomClassLoader("/Users/gaobin/work");
        Class<?> loadClass = classLoader.loadClass("com.learn.demo.math.ListNode1");
        System.out.println(111);
        System.out.println(loadClass.getClassLoader());
        System.out.println(loadClass.getClassLoader().getParent());

    }

    static class MyCustomClassLoader extends ClassLoader{

        String classPath;

        MyCustomClassLoader(String classPath){
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = loadByte(name);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int available = fis.available();
            byte[] by = new byte[available];
            fis.read(by);
            fis.close();
            return by;
        }
    }
}
