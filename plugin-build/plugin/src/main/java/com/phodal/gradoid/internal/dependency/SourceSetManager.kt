package com.phodal.gradoid.internal.dependency

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging

class SourceSetManager(project: Project) {
    private val configurations: ConfigurationContainer = project.configurations
    private val configuredSourceSets = mutableSetOf<String>()
    val sourceSetsContainer: NamedDomainObjectContainer<AndroidSourceSet> = project.container(
        AndroidSourceSet::class.java,
        AndroidSourceSetFactory(project))
    private val logger: Logger = Logging.getLogger(this.javaClass)

    fun setUpTestSourceSet(name: String): AndroidSourceSet {
        return setUpSourceSet(name, true)
    }

    @JvmOverloads
    fun setUpSourceSet(name: String, isTestComponent: Boolean = false): AndroidSourceSet {
        val sourceSet = sourceSetsContainer.maybeCreate(name)
        if (!configuredSourceSets.contains(name)) {
            createConfigurationsForSourceSet(sourceSet, isTestComponent)
            configuredSourceSets.add(name)
        }

        return sourceSet
    }

    private fun createConfigurationsForSourceSet(
        sourceSet: AndroidSourceSet,
        testComponent: Boolean
    ) {
        val apiName = sourceSet.getApiConfigurationName()
        val implementationName = sourceSet.getImplementationConfigurationName()
        val runtimeOnlyName = sourceSet.getRuntimeOnlyConfigurationName()
        val compileOnlyName = sourceSet.getCompileOnlyConfigurationName()

    }

}
