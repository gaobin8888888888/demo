package com.learn.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");

        int s = (int) se.eval("3+5");
        System.out.println(s);
    }

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
        System.out.println(engineFactories);
        ScriptEngine nashorn = manager.getEngineByName("nashorn");

        int s = (int) nashorn.eval("3+5");
        System.out.println(s);
        s = (int) nashorn.eval("3*5");
        System.out.println(s);

        int a = 4;
        int b = 9;
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("a", a);
        simpleBindings.put("b", b);
        String str = "a+b";
        Object globalValue = nashorn.eval(str, simpleBindings);
        System.out.println(globalValue);

        Map<String, Object> map = new HashMap<>();
        map.put("startTime", 1234);
        map.put("endTime", 1111);
        map.put("sss", "123");
        simpleBindings = new SimpleBindings(map);
        String str1 = "startTime % endTime == 123";
        System.out.println(nashorn.eval(str1, simpleBindings));
    }

}
