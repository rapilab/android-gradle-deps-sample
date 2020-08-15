package com.phodal.gradoid.internal.dependency

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer

class SourceSetManager(project: Project) {
    private val configurations: ConfigurationContainer = project.configurations
    val sourceSetsContainer: NamedDomainObjectContainer<AndroidSourceSet> = project.container(
        AndroidSourceSet::class.java,
        AndroidSourceSetFactory(project))

    @JvmOverloads
    fun setUpSourceSet(name: String) {
        val sourceSet = sourceSetsContainer.maybeCreate(name)
        createConfigurationsForSourceSet(sourceSet)
    }

    private fun createConfigurationsForSourceSet(sourceSet: AndroidSourceSet) {

    }

}
