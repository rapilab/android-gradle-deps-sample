package com.phodal.gradoid

import com.phodal.gradoid.internal.dependency.GenericTransformParameters
import com.phodal.gradoid.internal.dependency.IdentityTransform
import com.phodal.gradoid.internal.dependency.SourceSetManager
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.transform.TransformSpec
import org.gradle.api.component.SoftwareComponentFactory
import org.gradle.api.internal.artifacts.ArtifactAttributes
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import javax.inject.Inject

class AppPlugin: Plugin<Project> {
    private lateinit var variantManager: VariantManager
    private lateinit var taskManager: ApplicationTaskManager
    private lateinit var extension: AppExtension
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
        val sourceSetManager = SourceSetManager(project)
        this.extension = project.extensions.create("phodal", AppExtension::class.java, sourceSetManager)

        this.taskManager = ApplicationTaskManager(project, extension, registry);
        this.variantManager = VariantManager(project, extension, taskManager, sourceSetManager)

    }

    private fun createTasks() {
//        extension.getCompileSdkVersion()
//        DependencyConfigurator(project, project.name).configureDependencies()

        taskManager.createTasksBeforeEvaluate()
        createAndroidTasks()
    }

    private fun createAndroidTasks() {
        configDependencies()

        variantManager.createVariantsAndTasks()
    }

    private fun configDependencies() {
        val dependencies: DependencyHandler = project.dependencies
        dependencies.registerTransform(
                IdentityTransform::class.java
        ) { spec: TransformSpec<GenericTransformParameters> ->
            spec.parameters.projectName.set(project.name)
            spec.from.attribute(
                    ArtifactAttributes.ARTIFACT_FORMAT,
                    AndroidArtifacts.ArtifactType.JAR.type
            )
            spec.to.attribute(
                    ArtifactAttributes.ARTIFACT_FORMAT,
                    AndroidArtifacts.ArtifactType.PROCESSED_JAR.type
            )
        }

    }
}