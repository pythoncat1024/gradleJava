package com.intellij.cat.annotation.anotated;

import com.intellij.cat.annotation.Type;

@Type(params = "idea", pos = 1, value = {'a', 'b', 'm'})
public class TypeObject {

    @Type(pos = 3, value = 'd')
    private int add(int a, int b) {

        @Type(params = "potato", value = 'f', pos = 12)
        int c = a + b;
        return c;
    }

}
