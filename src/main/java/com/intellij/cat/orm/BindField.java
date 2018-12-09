package com.intellij.cat.orm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对表字段的注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindField {

    // `_uid` int(32) NOT NULL AUTO_INCREMENT,

    String columnName() default "";

    /**
     * 字段类型：varchar , int(25)
     *
     * @return field type
     */
    String type();

    boolean notNull() default false;

    boolean primaryKey() default false;

    boolean unique() default false;

    boolean autoIncrement() default false;
}
