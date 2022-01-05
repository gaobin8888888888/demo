package com.learn.demo.classLoad;


import java.io.FileInputStream;
import java.util.Objects;

/**
 * @author gaobin
 * @date 2021/12/31 11:35 上午
 * @desc 打破双亲委派机制实现
 */
public class MyBreakClassLoaderTest {

    public static void main(String[] args) throws Exception {
        MyBreakClassLoader classLoader = new MyBreakClassLoader("/Users/gaobin/work");
        Class<?> loadClass = classLoader.loadClass("com.learn.demo.math.ListNode1");
        System.out.println(111);
        System.out.println(loadClass.getClassLoader());
        System.out.println(loadClass.getClassLoader().getParent());

    }

    static class MyBreakClassLoader extends ClassLoader{

        String classPath;

        MyBreakClassLoader(String classPath){
            this.classPath = classPath;
        }

        @Override
        public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    //非自定义的类还是走双亲委派机制
                    if (!name.startsWith("com.learn.demo.math")){
                        c = this.getParent().loadClass(name);
                    } else {
                        c = findClass(name);
                    }
                }
                return c;
            }
        }

        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
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
