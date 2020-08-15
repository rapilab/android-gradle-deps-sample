package com.phodal.gradoid.internal.dependency

import com.phodal.gradoid.util.appendCapitalized
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet
import javax.inject.Inject

open class DefaultAndroidSourceSet @Inject constructor(private val name: String, project: Project): AndroidSourceSet {
    private val CONFIG_NAME_API = "api"
    private val CONFIG_NAME_COMPILE_ONLY = "compileOnly"
    private val CONFIG_NAME_IMPLEMENTATION = "implementation"
    private val CONFIG_NAME_RUNTIME_ONLY = "runtimeOnly"

    private fun getName(config: String): String {
        return if (name == SourceSet.MAIN_SOURCE_SET_NAME) {
            config
        } else {
            name.appendCapitalized(config)
        }
    }

    override fun getName(): String {
        return name
    }

    override fun getApiConfigurationName() = getName(CONFIG_NAME_API)

    override fun getCompileOnlyConfigurationName() = getName(CONFIG_NAME_COMPILE_ONLY)

    override fun getImplementationConfigurationName() = getName(CONFIG_NAME_IMPLEMENTATION)

    override fun getRuntimeOnlyConfigurationName() = getName(CONFIG_NAME_RUNTIME_ONLY)

}
