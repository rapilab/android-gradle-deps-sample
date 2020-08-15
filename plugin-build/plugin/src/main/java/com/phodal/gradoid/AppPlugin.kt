package com.phodal.gradoid

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.component.SoftwareComponentFactory
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import javax.inject.Inject

class AppPlugin: Plugin<Project> {
    private lateinit var project: Project

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
        this.project = project

//        Aapt2Workers.registerAapt2WorkersBuildService(project)
//        Aapt2Daemon.registerAapt2DaemonBuildService(project)

        configureProject();
        configureExtension();
        createTasks();
    }

    private fun configureProject() {
        project.plugins.apply(JavaBasePlugin::class.java)
    }

    private fun configureExtension() {

    }

    private fun createTasks() {


    }



}