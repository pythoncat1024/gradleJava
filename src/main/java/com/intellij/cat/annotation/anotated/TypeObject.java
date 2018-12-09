package com.intellij.cat.annotation.anotated;

import com.intellij.cat.annotation.Type;

@Type(params = "idea", pos = 1)
public class TypeObject {

    @Type(pos = 3)
    private int add(int a, int b) {

        @Type(params = "potato", pos = 12)
        int c = a + b;
        return c;
    }

}
