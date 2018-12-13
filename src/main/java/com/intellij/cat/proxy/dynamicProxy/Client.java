package com.intellij.cat.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {

        Star realStar = new RealStar(); // 真实类对象
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class[] interfaces = {Star.class};
        ProxyHandler handler = new ProxyHandler(realStar); // 代理工作区
        Object instance = Proxy.newProxyInstance(classLoader, interfaces, handler);
        Star proxy = (Star) instance;  // 动态生成的代理类对象
        System.out.println(proxy.getClass().getName());
        String result = proxy.sing("菊花台", "你的样子", "依然爱你", "分手快乐");

        System.out.println("Tony 唱得好不好?\t" + result);
    }
}
