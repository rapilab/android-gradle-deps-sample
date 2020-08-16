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

编译命令后加上--profile，比如 gradlew build --profile，会输出打包过程的profile report。


`androidx` 命名空间中的工件包含 Android [Jetpack](https://developer.android.com/jetpack?hl=zh-cn) 库。与支持库一样，`androidx` 命名空间中的库与 Android 平台分开提供，并向后兼容各个 Android 版本。

AndroidX 对原始 Android [支持库](https://developer.android.com/topic/libraries/support-library?hl=zh-cn)进行了重大改进，后者现在已不再维护。`androidx` 软件包完全取代了支持库，不仅提供同等的功能，而且提供了新的库。

此外，AndroidX 还包括以下功能：

*   AndroidX 中的所有软件包都使用一致的命名空间，以字符串 `androidx` 开头。支持库软件包已映射到对应的 `androidx.*` 软件包。有关所有旧类到新类以及旧构建工件到新构建工件的完整映射，请参阅[软件包重构](https://developer.android.com/jetpack/androidx/refactor?hl=zh-cn)页面。

*   与支持库不同，`androidx` 软件包会单独维护和更新。从版本 1.0.0 开始，`androidx` 软件包使用严格的[语义版本控制](https://semver.org/)。您可以单独更新项目中的各个 AndroidX 库。

*   [版本 28.0.0](https://developer.android.com/topic/libraries/support-library/revisions?hl=zh-cn#28-0-0) 是支持库的最后一个版本。我们将不再发布 `android.support` 库版本。所有新功能都将在 `androidx` 命名空间中开发。

## 增量编译

```java
private void performIncrementalCompilation(InputChanges inputs, DefaultJavaCompileSpec spec) {
    boolean isUsingCliCompiler = isUsingCliCompiler(spec);
    File sourceClassesMappingFile = getSourceClassesMappingFile();
    Multimap<String, String> oldMappings;
    SourceFileClassNameConverter sourceFileClassNameConverter;
    if (isUsingCliCompiler) {
        oldMappings = null;
        sourceFileClassNameConverter = new FileNameDerivingClassNameConverter();
    } else {
        oldMappings = readSourceClassesMappingFile(sourceClassesMappingFile);
        sourceFileClassNameConverter = new DefaultSourceFileClassNameConverter(oldMappings);
    }
    sourceClassesMappingFile.delete();
    spec.getCompileOptions().setIncrementalCompilationMappingFile(sourceClassesMappingFile);
    Compiler<JavaCompileSpec> compiler = createCompiler(spec);
    compiler = makeIncremental(inputs, sourceFileClassNameConverter, (CleaningJavaCompiler<JavaCompileSpec>) compiler, getStableSources().getAsFileTree());
    WorkResult workResult = performCompilation(spec, compiler);
    if (workResult instanceof IncrementalCompilationResult && !isUsingCliCompiler) {
        // The compilation will generate the new mapping file
        // Only merge old mappings into new mapping on incremental recompilation
        mergeIncrementalMappingsIntoOldMappings(sourceClassesMappingFile, getStableSources(), inputs, oldMappings);
    }
}
```

### Android 增量类：

```
AidlCompile.java
ColdswapArtifactsKickerTask.java
KickerTask.java
ManifestProcessorTask.java
MergeManifests.java
MergeResources.java
MergeSourceSetFolders.java
PackageApplication.java
PrePackageApplication.java
ProcessAndroidResources.java
ProcessManifest.java
ProcessTestManifest.java
```

基类主要逻辑：

```
@TaskAction
void taskAction(IncrementalTaskInputs inputs) throws IOException {
    if (!isIncremental()) {
        doFullTaskAction();
        return;
    }

    if (!inputs.isIncremental()) {
        getProject().getLogger().info("Unable do incremental execution: full task run");
        doFullTaskAction();
        return;
    }

    final Map<File, FileStatus> changedInputs = Maps.newHashMap();
    inputs.outOfDate(new Action<InputFileDetails>() {
        @Override
        public void execute(InputFileDetails change) {
            changedInputs.put(change.getFile(), change.isAdded() ? FileStatus.NEW : FileStatus.CHANGED);
        }
    });

    inputs.removed(new Action<InputFileDetails>() {
        @Override
        public void execute(InputFileDetails change) {

            changedInputs.put(change.getFile(), FileStatus.REMOVED);
        }
    });

    doIncrementalTaskAction(changedInputs);
}
```


`DependencyConfigurator.kt` 注释：

```
// When consuming classes from Android libraries, there are 2 transforms:
//     1. `android-classes-directory` -> `android-classes`
//     2. `android-classes-jar` -> `android-classes`
// Currently Gradle always takes transform flow #1, which is ideal for incremental dexing.
// (We don't know why Gradle does that, but IncrementalDesugaringTest should catch it if
// this behavior changes.)



// When consuming classes from Java libraries, there are 2 transforms:
//     1. `java-classes-directory` -> `android-classes`
//     2. `jar` -> `processed-jar` -> `android-classes-jar` -> `android-classes`
// Currently Gradle always takes transform flow #2, which is not ideal for incremental
// dexing.
// TODO(147137579): Configure Gradle to take transform flow #1.
```

### androidTestImplementation

```kotlin
private fun createConfiguration(
        name: String, description: String, canBeResolved: Boolean = false): Configuration {
    logger.debug("Creating configuration {}", name)

    val configuration = configurations.maybeCreate(name)

    configuration.isVisible = false
    configuration.description = description
    configuration.isCanBeConsumed = false
    configuration.isCanBeResolved = canBeResolved

    return configuration
}
```