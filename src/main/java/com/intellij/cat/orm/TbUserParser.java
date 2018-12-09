package com.intellij.cat.orm;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * 自定义注解解析类
 */
public class TbUserParser {

    private TbUserParser() {
    }

    private static TableInfo parseClass() {

        BindTable annotation = (BindTable) ((Class) User.class).getAnnotation(BindTable.class);
//        System.out.println(annotation);
        String charset = annotation.charset();
        String tbName = annotation.value();
        return new TableInfo(charset, tbName);

    }


    private static List<FieldInfo> parserField() {

        Field[] fields = User.class.getDeclaredFields();
        ArrayList<FieldInfo> infoSet = new ArrayList<>();

        for (Field f : fields) {

            // 没有添加该注解的字段就会返回 null
            @Nullable
            BindField bindF = f.getAnnotation(BindField.class);

            // System.out.println("bindF==" + bindF);
            if (Objects.nonNull(bindF)) {
                String columnName = bindF.columnName();
                String type = bindF.type();
                boolean unique = bindF.unique();
                boolean autoIncrement = bindF.autoIncrement();
                boolean notNull = bindF.notNull();
                boolean primaryKey = bindF.primaryKey();
                FieldInfo info = new FieldInfo(columnName, type, notNull,
                        autoIncrement, unique, primaryKey);
                infoSet.add(info);
            }
        }
        return infoSet;
    }


    public static String createTable() {
        TableInfo tableInfo = parseClass();
        List<FieldInfo> fieldInfoSet = parserField();
        String head = String.format(Locale.getDefault(),
                "CREATE TABLE IF NOT EXISTS %s(", tableInfo.tbName);
        StringBuilder item = new StringBuilder();
        for (FieldInfo fi : fieldInfoSet) {
            // `chat_id` INT UNSIGNED AUTO_INCREMENT,
            item.append(String.format(Locale.getDefault(), "`%s` ", fi.columnName))
                    .append(String.format(Locale.getDefault(), "%s ", fi.type))
                    .append(fi.primaryKey ? "PRIMARY KEY " : "")
                    .append(fi.notNull ? "NOT NULL " : "")
                    .append(fi.unique ? "UNIQUE " : "")
                    .append(fi.autoIncrement ? "AUTO_INCREMENT," : ",");
        }
        if (item.charAt(item.length() - 1) == ',') {
            item.deleteCharAt(item.length() - 1);
        }
        String tail = String.format(Locale.getDefault(), ") CHARSET=%s;", tableInfo.charset);
        return head + item + tail;
    }


    static class TableInfo {
        String charset;
        String tbName;

        TableInfo(String charset, String tbName) {
            this.charset = charset;
            this.tbName = tbName;
        }
    }

    static class FieldInfo {
        String columnName;
        String type;
        boolean notNull;
        boolean autoIncrement;
        boolean unique;
        boolean primaryKey;

        FieldInfo(String columnName, String type, boolean notNull,
                  boolean autoIncrement, boolean unique, boolean primaryKey) {
            this.columnName = columnName;
            this.type = type;
            this.notNull = notNull;
            this.autoIncrement = autoIncrement;
            this.unique = unique;
            this.primaryKey = primaryKey;
        }
    }
}
