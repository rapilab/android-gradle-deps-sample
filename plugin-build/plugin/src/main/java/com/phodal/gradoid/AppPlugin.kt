package com.phodal.gradoid

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.component.SoftwareComponentFactory
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import javax.inject.Inject

class AppPlugin: Plugin<Project> {
    private var componentFactory: SoftwareComponentFactory?
    private var registry: ToolingModelBuilderRegistry?

    @Inject
    constructor(
        registry: ToolingModelBuilderRegistry?,
        componentFactory: SoftwareComponentFactory?
    ) {
        this.registry = registry
        this.componentFactory = componentFactory
    }

    override fun apply(project: Project) {
        basePluginApply(project)
        pluginSpecificApply(project)
    }

    private fun pluginSpecificApply(project: Project) {

    }

    private fun basePluginApply(project: Project) {

    }

}