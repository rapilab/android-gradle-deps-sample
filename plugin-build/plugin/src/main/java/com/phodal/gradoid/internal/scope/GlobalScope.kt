package com.phodal.gradoid.internal.scope

import com.google.common.base.Preconditions
import com.phodal.gradoid.AppExtension
import org.gradle.api.Project

class GlobalScope(project: Project) {
    private lateinit var extension: AppExtension

    fun setExtension(extension: AppExtension) {
        this.extension = Preconditions.checkNotNull(extension)
    }
}