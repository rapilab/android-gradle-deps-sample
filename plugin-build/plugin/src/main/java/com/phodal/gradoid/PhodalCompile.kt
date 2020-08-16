package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceSet
import org.gradle.api.JavaVersion
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.incremental.IncrementalTaskInputs
import java.io.File

open class PhodalCompile() : JavaCompile() {
    private val classesOutputDirectory = project.objects.directoryProperty()

    fun configure() {
        val javaSourceSet = DefaultAndroidSourceSet("main", project)
        val sourceSets = ImmutableList.builder<ConfigurableFileTree>()
        sourceSets.addAll(javaSourceSet.javaSource.getSourceDirectoryTrees()!!)
        val source = sourceSets.build()

        val javaSource = mutableListOf<FileTree>()
        javaSource.addAll(source)

        this.classpath = project.files(File("lib"))

        this.source = this.project.files(javaSource.toList()).asFileTree
        this.destinationDir = File("build/classes")
    }

    override fun compile(inputs: IncrementalTaskInputs) {
        println("compile")
        println(JavaVersion.current().isJava8Compatible)
        super.compile(inputs)
    }
}
