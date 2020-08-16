package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceSet
import org.gradle.api.Action
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import java.io.File

open class PhodalCompile() : JavaCompile() {
    fun configure() {
        val javaSourceSet = DefaultAndroidSourceSet("main", project)
        val sourceSets = ImmutableList.builder<ConfigurableFileTree>()
        sourceSets.addAll(javaSourceSet.javaSource.getSourceDirectoryTrees()!!)
        val source = sourceSets.build()

        val javaSource = mutableListOf<FileTree>()
        javaSource.addAll(source)

        this.classpath = project.files(File("libs"))

        val config = createConfiguration()
        config.dependencies.whenObjectAdded(SomeConfigurationAction())

        val message = project.configurations.getByName("zzz")
        println(message)


        this.source = this.project.files(javaSource.toList()).asFileTree
        this.destinationDir = File("build/classes")
//        this.options.bootstrapClasspath => android.jar
    }

    private fun createConfiguration(): Configuration {
        val configuration = project.configurations.maybeCreate("zzz")
        configuration.isVisible = false
        configuration.description = description
        configuration.isCanBeConsumed = false
        configuration.isCanBeResolved = false

        return configuration
    }

    override fun compile(inputs: IncrementalTaskInputs) {
        super.compile(inputs)
    }
}

class SomeConfigurationAction: Action<Dependency> {
    override fun execute(t: Dependency) {
        println(t)
    }

}
