package com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.phodal.gradoid.internal.api.DefaultAndroidSourceDirectorySet
import com.phodal.gradoid.internal.api.SourceArtifactType
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.file.FileTree
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
        val task = this.taskContainer.create("phodalJavaCompile", PhodalCompile::class.java)
        task.configure()

        println(task)
    }

    private fun handleMicroApp() {

    }

    private fun createAnchorTasks() {

    }

}
