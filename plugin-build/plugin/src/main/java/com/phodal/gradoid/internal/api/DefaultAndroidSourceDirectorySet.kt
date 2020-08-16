package com.phodal.gradoid.internal.api

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.Lists
import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTreeElement
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.util.PatternFilterable
import org.gradle.api.tasks.util.PatternSet
import java.util.*

class DefaultAndroidSourceDirectorySet(
    private val name: String,
    private val project: Project,
    private val type: SourceArtifactType
) :
    AndroidSourceDirectorySet {
    val source = Lists.newArrayList<Any>()
    val filter = PatternSet()

    override fun getFilter(): PatternFilterable {
        return filter
    }

    override fun srcDir(srcDir: Any): AndroidSourceDirectorySet {
        source.add(srcDir)
        return this
    }

    override fun srcDirs(vararg srcDirs: Any): AndroidSourceDirectorySet {
        Collections.addAll(source, *srcDirs)
        return this
    }

    override fun setSrcDirs(srcDirs: Iterable<*>): AndroidSourceDirectorySet {
        source.clear()
        for (srcDir in srcDirs) {
            source.add(srcDir)
        }
        return this
    }

    override fun getIncludes(): Set<String> {
        return filter.includes
    }

    override fun getExcludes(): Set<String> {
        return filter.excludes
    }

    override fun setIncludes(includes: Iterable<String>): PatternFilterable {
        filter.setIncludes(includes)
        return this
    }

    override fun setExcludes(excludes: Iterable<String>): PatternFilterable {
        filter.setExcludes(excludes)
        return this
    }

    override fun include(vararg includes: String): PatternFilterable {
        filter.include(*includes)
        return this
    }

    override fun include(includes: Iterable<String>): PatternFilterable {
        filter.include(includes)
        return this
    }

    override fun include(includeSpec: Spec<FileTreeElement>): PatternFilterable {
        filter.include(includeSpec)
        return this
    }

    override fun include(includeSpec: Closure<*>): PatternFilterable {
        filter.include(includeSpec)
        return this
    }

    override fun exclude(excludes: Iterable<String>): PatternFilterable {
        filter.exclude(excludes)
        return this
    }

    override fun exclude(vararg excludes: String): PatternFilterable {
        filter.exclude(*excludes)
        return this
    }

    override fun exclude(excludeSpec: Spec<FileTreeElement>): PatternFilterable {
        filter.exclude(excludeSpec)
        return this
    }

    override fun exclude(excludeSpec: Closure<*>): PatternFilterable {
        filter.exclude(excludeSpec)
        return this
    }

    fun getSourceDirectoryTrees(): List<ConfigurableFileTree> {
        return source.stream()
                .map { sourceDir ->
                    project.fileTree(
                            ImmutableMap.of(
                                    "dir", sourceDir,
                                    "includes", includes,
                                    "excludes", excludes))
                }
                .collect(ImmutableList.toImmutableList())
    }
}