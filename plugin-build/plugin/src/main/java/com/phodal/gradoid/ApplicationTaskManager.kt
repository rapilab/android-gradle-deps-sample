package com.phodal.gradoid

import org.gradle.api.Project
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry

class ApplicationTaskManager(project: Project, extension: AppExtension, registry: ToolingModelBuilderRegistry?) {
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
        createCompileTask()

        //...
    }

    private fun createCompileTask() {
        createJavacTask()
    }

    private fun createJavacTask() {

    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

}
