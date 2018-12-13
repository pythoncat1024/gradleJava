package com.intellij.cat.proxy.staticProxy;

public class Client {

    public static void main(String[] args) {


        Star real = new RealStar();
        Star proxy = new ProxyStar(real);
        String result = proxy.sing("成都", "李香兰", "千里之外");

        System.out.println("唱得如何？" + result);

    }
}
