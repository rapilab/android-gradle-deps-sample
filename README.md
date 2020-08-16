# Android Gradle dependencies learn

 - 官方文档：[配置构建变体](https://developer.android.com/studio/build/build-variants?hl=zh-cn)
 - [sourceSets——Android Gadle 入门](https://juejin.im/post/6844904003478765575)
 - Gradle 增量编译：[Incremental tasks](https://docs.gradle.org/current/userguide/custom_tasks.html#incremental_tasks)
 - Groovy 元编程：[Runtime and compile-time metaprogramming](http://groovy-lang.org/metaprogramming.html#_methodmissing)


```
android {
  ...
  sourceSets {
    // Encapsulates configurations for the main source set.
    main {
      // Changes the directory for Java sources. The default directory is
      // 'src/main/java'.
      java.srcDirs = ['other/java']

      // If you list multiple directories, Gradle uses all of them to collect
      // sources. Because Gradle gives these directories equal priority, if
      // you define the same resource in more than one directory, you get an
      // error when merging resources. The default directory is 'src/main/res'.
      res.srcDirs = ['other/res1', 'other/res2']

      // Note: You should avoid specifying a directory which is a parent to one
      // or more other directories you specify. For example, avoid the following:
      // res.srcDirs = ['other/res1', 'other/res1/layouts', 'other/res1/strings']
      // You should specify either only the root 'other/res1' directory, or only the
      // nested 'other/res1/layouts' and 'other/res1/strings' directories.

      // For each source set, you can specify only one Android manifest.
      // By default, Android Studio creates a manifest for your main source
      // set in the src/main/ directory.
      manifest.srcFile 'other/AndroidManifest.xml'
      ...
    }
  ...
```

## Setup

Install JDK 1.6

```bash
brew cask install java6
```

