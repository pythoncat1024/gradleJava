package com.intellij.cat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注解：target -> class , method , local_var
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Type {

    String params() default "intelliJ";
}
