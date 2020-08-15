package com.phodal.gradoid

import com.phodal.gradoid.internal.tasks.factory.TaskFactory
import com.phodal.gradoid.internal.tasks.factory.TaskFactoryImpl
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

class ApplicationTaskManager(project: Project, extension: AppExtension, registry: ToolingModelBuilderRegistry?) {
    protected val taskFactory: TaskFactory? = TaskFactoryImpl(project.tasks)

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

    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

}
