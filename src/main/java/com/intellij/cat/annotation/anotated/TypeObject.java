package com.intellij.cat.annotation.anotated;

import com.intellij.cat.annotation.Type;

@Type(params = "idea")
public class TypeObject {

    @Type
    private int add(int a, int b) {

        @Type(params = "potato")
        int c = a + b;
        return c;
    }

}
