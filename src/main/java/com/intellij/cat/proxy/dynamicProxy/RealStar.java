package com.intellij.cat.proxy.dynamicProxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class RealStar implements Star {

    @Override
    public String sing(String... song) {
        System.err.println("I am real star , sing song:" + Arrays.toString(song));
        return "very nice !!!";
    }
}
