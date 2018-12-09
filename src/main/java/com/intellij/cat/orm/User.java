package com.intellij.cat.orm;

/**
 * 对应到 数据库中的一条记录
 */
@BindTable(value = "tb_user")
public class User {

    public static final int GENDER_SECRET = 0;
    public static final int GENDER_MAIL = 1;
    public static final int GENDER_FEMAIL = 2;

    @BindField(type = "varchar(50)", notNull = true, columnName = "_name")
    private String name;
    @BindField(type = "int(3)", columnName = "_age")
    private int age;
    @BindField(type = "int(1)", notNull = true, columnName = "_gender")
    private int gender;

    @BindField(type = "int(1)", primaryKey = true, columnName = "_uid")
    private long uid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
