package com.intellij.cat.generic;


import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import org.apache.commons.lang3.tuple.Pair;
import sun.reflect.generics.tree.ReturnType;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Sample {

    @com.intellij.cat.annotation.Type(pos = 1, value = 'c')
    @BindType
    private Pair<String, User> filterInfo(@BindType String key
            , @BindType Map<String, User> map) {
        @BindType
        int x = 9;
        x += 1;
        return map.entrySet().stream()
                .filter(entry -> entry.getKey().equals(key))
                .findAny()
                .flatMap(entry -> Optional.of(Pair.of(entry.getKey(), entry.getValue())))
                .orElseThrow(() -> new NullPointerException(" get null "));
    }


    @NonNull
    @com.intellij.cat.annotation.Type(pos = 1, value = 'c')
    private Map<String, User> generateData() {
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put("k1", new User.Builder().appendName("name1").build());
        hashMap.put("k2", new User.Builder().appendName("name2").build());
        hashMap.put("k3", new User.Builder().appendName("name3").build());
        return hashMap;
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        Sample sample = new Sample();
        Map<String, User> userMap = sample.generateData();
        Pair<String, User> k1 = sample.filterInfo("k1", userMap);
        Class aClass = Class.forName("com.intellij.cat.generic.Sample");

        Method generateData = aClass.getDeclaredMethod("generateData"); // ok
        generateData.setAccessible(true);

        Method filterInfo = aClass.getDeclaredMethod("filterInfo", String.class, Map.class);

        Class<?> returnType = filterInfo.getReturnType();

        Class<?>[] parameterTypes = filterInfo.getParameterTypes();

        for (Class c:parameterTypes){
            Type[] types = c.getGenericInterfaces();
            System.out.println("param："+ Arrays.toString(types));
        }

    }


    @Nullable
    private User user;
}
