package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.google.common.collect.Sets
import com.phodal.gradoid.internal.api.DefaultAndroidSourceSet
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ResolutionStrategy
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.work.InputChanges
import java.io.File

open class PhodalCompile() : JavaCompile() {
    var compileClasspaths: MutableSet<Configuration> = mutableSetOf()

    fun configure() {
        val javaSourceSet = DefaultAndroidSourceSet("main", project)
        val sourceSets = ImmutableList.builder<ConfigurableFileTree>()
        sourceSets.addAll(javaSourceSet.javaSource.getSourceDirectoryTrees()!!)
        val source = sourceSets.build()

        val javaSource = mutableListOf<FileTree>()
        javaSource.addAll(source)

        val configurations = project.configurations
        val config: Configuration = configurations.getByName("implementation")
        val compileClasspath: Configuration = configurations.getByName("phodalCompileClasspath")

        compileClasspaths.add(config)
        compileClasspaths.add(compileClasspath)

        val finalPath = configurations.maybeCreate("finalCompileClasspath")
        finalPath.isVisible = false
        finalPath.description = "Resolved configuration for compilation for variant"
        finalPath.setExtendsFrom(compileClasspaths)
        finalPath.isCanBeConsumed = false
        finalPath.resolutionStrategy.sortArtifacts(ResolutionStrategy.SortOrder.CONSUMER_FIRST)

        this.classpath = finalPath

        this.source = this.project.files(javaSource.toList()).asFileTree

        this.outputs.dir("build/classes")
        this.destinationDir = File("build/classes")

        this.options.isIncremental = true
//        this.options.bootstrapClasspath => android.jar
    }

    override fun compile(inputs: InputChanges) {
        super.compile(inputs)
    }
}
