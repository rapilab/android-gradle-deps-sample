package com.phodal.gradoid

import com.phodal.gradoid.internal.dependency.SourceSetManager

open class AppExtension(sourceSetManager: SourceSetManager) {
    fun getCompileSdkVersion() {

    }

    init {
        sourceSetManager.setUpSourceSet("main")

        sourceSetManager.setUpTestSourceSet("androidTest")
        sourceSetManager.setUpTestSourceSet("test")
    }
}
