package com.phodal.gradoid

import com.phodal.gradoid.internal.dependency.SourceSetManager
import com.phodal.gradoid.internal.tasks.factory.TaskFactoryImpl
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile
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
        val task = this.taskContainer.register("phodalJavaCompile")
        println(task)
    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

}
