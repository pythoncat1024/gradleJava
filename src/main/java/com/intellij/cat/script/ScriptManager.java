package com.intellij.cat.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScriptManager {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {

        // 获取对应的脚本引擎对象
        ScriptEngineManager sm = new ScriptEngineManager();

        ScriptEngine engine = sm.getEngineByName("javascript");

        // 定义变量，存储到引擎的上下文里面( 可以被js, java 获取)
        engine.put("msg","you are kidding me!");

        String str = "var user = {name:'tom'};";

        str += "print(user.name)";

        // 执行脚本
        engine.eval(str); // 控制台输出：tom

        engine.eval("msg = 'i am stone';");

        System.out.println(engine.get("msg")); // 输出 i am stone

        // 定义 js 函数
        engine.eval("function add(a,b){return a+b;}");

        Invocable jsInvoke = (Invocable) engine;
        // 执行 js 函数
        Object result = jsInvoke.invokeFunction("add", new Object[]{1, 2});
        System.out.println("result="+result); // result=3.0

        // js -> 使用 Java 对象作为参数，
        engine.eval(new FileReader("main.js")); // 在 main.js 里面定义一个函数 printJavaList
        List<String> stringList = new ArrayList<>(Arrays.asList("汤姆丁", "杰克陈", "布鲁斯李"));
        List<String> list = (List<String>) jsInvoke.invokeFunction("printJavaList", stringList);
        System.out.println(list.getClass()+" ############ ");
        list.add("比亚迪车主");
        jsInvoke.invokeFunction("printJavaList", list);

    }

}
