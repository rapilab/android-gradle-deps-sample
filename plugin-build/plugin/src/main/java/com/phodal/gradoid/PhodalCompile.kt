package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceSet
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import org.gradle.work.InputChanges
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

        this.source = this.project.files(javaSource.toList()).asFileTree
        this.destinationDir = File("build/classes")

        this.options.isIncremental = true
//        this.options.bootstrapClasspath => android.jar
    }

    override fun compile(inputs: InputChanges) {
        super.compile(inputs)
    }
}
