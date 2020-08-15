package com.phodal.gradoid

import com.phodal.gradoid.internal.dependency.SourceSetManager
import org.gradle.api.Project

class VariantManager(
    val project: Project,
    val extension: AppExtension,
    val taskManager: ApplicationTaskManager,
    val sourceSetManager: SourceSetManager
) {
    fun createVariantsAndTasks() {
        createTasksForVariant()
    }

    private fun createTasksForVariant() {
        taskManager.createAssembleTask()

        taskManager.createBundleTask()

        taskManager.createTasksForVariantScope()
    }

}
