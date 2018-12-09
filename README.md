# gradleJava
在 IntelliJ IDEA 里面配置可使用 gradle 依赖的 Java 项目

创建步骤：

* 打开 idea , 菜单栏 File -> NEW -> Project... -> 选择 gradle , sdk 选择 java1.8 , additional lib 勾选 Java 。
* Next -> 填写 ArtifactId (也就是当前项目名) -> Next -> Next -> Next -> Finish

经过以上步骤，就生成了一个支持 gradle 依赖的 Java 项目。

然后可以发现，右侧有 类似 android studio 中的 gradle 操作栏。 里面有 clean/build/assemble 这些 task 按钮。


> ps: 第一次可能会失败，再创建一次。

然后可以在 `build.gradle`文件的 `dependencies{}` 里面添加一些依赖。

比如：

```gradle
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'

    implementation "io.reactivex.rxjava2:rxjava:2.2.4"
    implementation 'com.google.guava:guava:27.0.1-jre'
}
```

一般来说，依赖添加之后，idea 会自动下载这些依赖包，如果没有自动下载，点击右侧的 gradle 菜单，有一个 sync 图标，点击之后，可以手动触发下载。
