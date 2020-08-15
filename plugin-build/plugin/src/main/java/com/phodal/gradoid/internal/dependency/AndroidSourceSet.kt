package com.phodal.gradoid.internal.dependency

interface AndroidSourceSet {
    fun getName(): String
    fun getApiConfigurationName(): String?
    fun getImplementationConfigurationName(): String?
    fun getRuntimeOnlyConfigurationName(): String?
    fun getCompileOnlyConfigurationName(): String?
}
