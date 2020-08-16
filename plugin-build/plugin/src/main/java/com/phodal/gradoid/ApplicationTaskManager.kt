package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceDirectorySet
import com.phodal.gradoid.internal.api.SourceArtifactType
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskContainer
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry
import java.io.File

class ApplicationTaskManager(val project: Project, extension: AppExtension, registry: ToolingModelBuilderRegistry?) {
    private var taskContainer: TaskContainer = project.tasks

    //    protected val taskFactory: TaskFactory? = TaskFactoryImpl(project.tasks)
    fun createTasksBeforeEvaluate() {

    }

    fun createAssembleTask() {

    }

    fun createBundleTask() {

    }

    fun createTasksForVariantScope() {
        createAnchorTasks()
        handleMicroApp()

        //...

        // Add a compile task
        createCompileTask()

        //...
    }

    private fun createCompileTask() {
//        val javacTask: TaskProvider<out JavaCompile?> = createJavacTask()
        createJavacTask()
    }

    private fun createJavacTask() {

        val config = createConfiguration()
        config.dependencies.whenObjectAdded(SomeConfigurationAction())

        val message = project.configurations.getByName("zzz")
        println(message)

        val task = this.taskContainer.create("phodalJavaCompile", PhodalCompile::class.java)
        task.configure()
    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

    private fun createConfiguration(): Configuration {
        val configuration = project.configurations.maybeCreate("zzz")
        configuration.isVisible = false
        configuration.description = "description"
        configuration.isCanBeConsumed = false
        configuration.isCanBeResolved = false

        return configuration
    }
}

class SomeConfigurationAction: Action<Dependency> {
    override fun execute(t: Dependency) {
        println(t)
    }

}
