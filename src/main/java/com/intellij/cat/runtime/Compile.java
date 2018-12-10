package com.intellij.cat.runtime;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 未经验证，只是模型
 */
public class Compile {

    /**
     * 编译 java 文件
     *
     * @param paths 需要被编译的文件路径数组
     * @return 1 success ， 0 fail
     * <p>
     * success ==> 生成的 xx.class 与 xx.java 在同一个目录下
     * </p>
     */
    public static int compile(String... paths) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        return compiler.run(null, null, null, paths);
    }

    public static Class loadClass(String jdkDir, String... paths)
            throws MalformedURLException, ClassNotFoundException {
        URL[] urls = {new URL("file:/" + jdkDir)};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        return urlClassLoader.loadClass(paths[0]);
    }

    public static void exec() throws IOException {

        Runtime runtime = Runtime.getRuntime();
        // xx.class 被执行
        Process java_xx = runtime.exec("java xx");
        InputStream inputStream = java_xx.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

    }
}
