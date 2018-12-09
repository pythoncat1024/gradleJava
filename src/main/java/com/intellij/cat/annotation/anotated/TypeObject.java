package com.intellij.cat.annotation.anotated;

import com.intellij.cat.annotation.Type;

@Type
public class TypeObject {

    @Type
    private int add(int a, int b) {

        @Type
        int c = a + b;
        return c;
    }

}
