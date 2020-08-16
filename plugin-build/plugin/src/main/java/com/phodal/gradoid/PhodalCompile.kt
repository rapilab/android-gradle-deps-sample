package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceDirectorySet
import com.phodal.gradoid.internal.api.SourceArtifactType
import groovy.lang.Closure
import org.gradle.api.JavaVersion
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

open class PhodalCompile() : JavaCompile() {
    fun configure(): FileTree {
        println("configure")
        val defaultAndroidSourceDirectorySet = DefaultAndroidSourceDirectorySet(
                "Java source", project, SourceArtifactType.JAVA_SOURCES
        )

        // Build the list of source folders.
        val sourceSets = ImmutableList.builder<ConfigurableFileTree>()
        sourceSets.addAll(defaultAndroidSourceDirectorySet.getSourceDirectoryTrees())
        val source = sourceSets.build()

        val javaSource = mutableListOf<FileTree>()
        javaSource.addAll(source)

        val jsource = this.project.files(javaSource.toList()).asFileTree
        return jsource
    }

    override fun compile(inputs: IncrementalTaskInputs) {
        println("compile")
        println(JavaVersion.current().isJava8Compatible)
        super.compile(inputs)
    }
}
