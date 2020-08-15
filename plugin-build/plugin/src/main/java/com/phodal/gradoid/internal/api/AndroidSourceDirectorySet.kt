package com.phodal.gradoid.internal.api

import org.gradle.api.tasks.util.PatternFilterable


interface AndroidSourceDirectorySet: PatternFilterable {
    fun getFilter(): PatternFilterable
    fun srcDir(srcDir: Any): AndroidSourceDirectorySet
    fun srcDirs(vararg srcDirs: Any): AndroidSourceDirectorySet
    fun setSrcDirs(srcDirs: Iterable<*>): AndroidSourceDirectorySet
}
