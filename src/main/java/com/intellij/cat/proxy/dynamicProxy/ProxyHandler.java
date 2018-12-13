package com.intellij.cat.proxy.dynamicProxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {

    private Star realStar;

    public ProxyHandler(Star realStar) {
        // 真实类对象（被代理类的对象）
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 那还是要先订机票咯
        System.out.println("我帮 Tony 订个机票，他要去成都唱歌...");
        // 通过反射调用真实类的方法 --> 这里对应的就是 sing 方法
        Object invoke = method.invoke(realStar, args);
        Thread.sleep(100);
        // 唱完了，我还是要帮忙收钱咯
        System.out.println("Tony 唱完了，该我收钱了。");
        return invoke;
    }
}
