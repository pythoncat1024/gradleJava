package com.intellij.cat.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BindTable {

    /**
     * table name
     *
     * @return table name
     */
    String value();

    String charset() default "utf8";
}
