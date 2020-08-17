package com.phodal.gradoid

import com.phodal.gradoid.internal.dependency.GenericTransformParameters
import com.phodal.gradoid.internal.dependency.IdentityTransform
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.transform.TransformSpec
import org.gradle.api.internal.artifacts.ArtifactAttributes
import org.gradle.api.tasks.TaskContainer
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

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

        val compile = createConfiguration("implementation", true)
        compile.dependencies.whenObjectAdded(SomeImplementationAction())

        val phodalCompile = createConfiguration("phodalImplementation", true)
        phodalCompile.dependencies.whenObjectAdded(SomeImplementationAction())

        val task = this.taskContainer.create("phodalJavaCompile", PhodalCompile::class.java)
        task.configure()
    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

    private fun createConfiguration(name: String, canBeResolved: Boolean = false): Configuration {
        val configuration = project.configurations.maybeCreate(name)
        configuration.isVisible = false
        configuration.description = "description"
        configuration.isCanBeConsumed = false
        configuration.isCanBeResolved = canBeResolved

        return configuration
    }
}

class SomeImplementationAction : Action<Dependency> {
    override fun execute(t: Dependency) {
        println(t)
    }
}

class SomeConfigurationAction : Action<Dependency> {
    override fun execute(t: Dependency) {
        println(t)
    }

}
