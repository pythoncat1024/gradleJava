package com.intellij.cat.assist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * http://www.javassist.org/tutorial/tutorial.html
 */
public class JavaAssistDemo {

    public static void main(String[] args) throws Throwable {

        System.out.println(String.class.getName());
        createDynamicClass();

        //   ####################
//        modifyExistsClass(); // 由于 toClass() 之后，不能再调用 ctClass 的任何 api ,所有这个方法和下面的方法不能同时调用
        // =============================
        insert2Method();

    }

    /**
     * 在已有方法调用前插入代码 ，insertBefore ok ; insertAfter fail
     */
    private static void insert2Method()
            throws NotFoundException, CannotCompileException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass target = pool.get("com.intellij.cat.assist.StaticUser");

        CtClass stringType = pool.get(String.class.getName());
        CtMethod useless = target.getDeclaredMethod("useless", new CtClass[]{stringType});
        // 构造一个新方法，专门用来打印日志的
        String logSrc = "public void log(String info){System.err.println(info);}";
        CtMethod logMethod = CtMethod.make(logSrc, target);
        target.addMethod(logMethod); // 先把新方法添加到类中(其实是添加到字节码里面)

        useless.insertBefore("System.err.println(\"before...useless call\");"); // ok
        useless.insertBefore("log(\"log before...useless call\");"); // ok
//        useless.insertAfter("System.err.println(\"456 abc \");");  //  insertAfter 会报错，不清楚原因！！！

        Class<StaticUser> clazz = target.toClass();

//        System.out.println(target);
//        System.out.println(clazz);
        Constructor con = clazz.getDeclaredConstructor(int.class, String.class);
        StaticUser obj = (StaticUser) con.newInstance(27, "吉姆");
        obj.useless("无用的方法了..."); // 会先打印 before...useless call
    }


    /**
     * 修改已有类，在里面添加方法 并调用该方法
     *
     * @throws NotFoundException         e
     * @throws CannotCompileException    e
     * @throws NoSuchMethodException     e
     * @throws InstantiationException    e
     * @throws IllegalAccessException    e
     * @throws InvocationTargetException e
     */
    private static void modifyExistsClass()
            throws NotFoundException, CannotCompileException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException, InvocationTargetException {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.intellij.cat.assist.StaticUser");
        System.out.println(ctClass);
        CtClass stringCtClass = pool.get(String.class.getName());
        CtClass[] parameters = {stringCtClass, CtClass.intType};
        CtMethod add = new CtMethod(stringCtClass, "add", parameters, ctClass);
        add.setModifiers(Modifier.PUBLIC); // 设置方法为 public
        add.setBody("{return $1+$2;}"); // {$0:this,$1:arg1,$2:arg2}
        // 这里特别注意，对于方法参数，$1 表示参数1，$2 表示参数2 ，$0 表示 this 对象
        ctClass.addMethod(add);

        Class<StaticUser> clazz = ctClass.toClass();
        Constructor<StaticUser> cr = clazz.getConstructor(int.class, String.class);
        StaticUser sUser = cr.newInstance(13, "sUser");
        Method clazzMethod = clazz.getMethod("add", String.class, int.class);
        String strResult = (String) clazzMethod.invoke(sUser, "tom's age is: ", 34);
        System.out.println("result of add==>" + strResult);
    }

    /**
     * 生成一个 不存在的 class 对象。DynamicUser.class
     *
     * @throws CannotCompileException e
     * @throws NotFoundException      e
     * @throws IOException            e
     */
    private static void createDynamicClass() throws CannotCompileException, NotFoundException, IOException {
        ClassPool pool = ClassPool.getDefault();

        // 生成一个 User.class
        CtClass user = pool.makeClass("DynamicUser");

        CtField name = CtField.make("private String name;", user);
        CtField age = CtField.make("private int age = 32;", user);
        user.addField(name);
        user.addField(age); // 添加成员变量

        CtClass[] parameters = {CtClass.intType, pool.get("java.lang.String")};
        CtConstructor con1 = new CtConstructor(parameters, user);
        con1.setBody("{this.name = name;this.age = age;}");
        user.addConstructor(con1); // 添加带参构造
        CtConstructor con2 = new CtConstructor(new CtClass[]{}, user);
        user.addConstructor(con2);


        CtMethod setName = CtMethod.make("public void setName(String name){this.name = name;}", user);
        CtMethod getName = CtMethod.make("public String getName(){return this.name;}", user);
        CtMethod setAge = CtMethod.make("public void setAge(int age){this.age = age;}", user);
        CtMethod getAge = CtMethod.make("public int getAge(){return this.age;}", user);
        user.addMethod(setAge);
        user.addMethod(setName);
        user.addMethod(getAge);
        user.addMethod(getName); // 添加成员方法
        CtMethod toString = CtMethod.make("public String toString(){return name+\"### \"+age;}", user);
        user.addMethod(toString);
        user.writeFile(); // 这一步只是用来检测是否生成成功了，其实可以不调用
        System.out.println("create DynamicUser.class success ?"); // 确实生成成功了
    }
}
