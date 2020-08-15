package com.phodal.gradoid.internal.dependency

import org.gradle.api.Project
import javax.inject.Inject

class DefaultAndroidSourceSet @Inject constructor(private val name: String, project: Project): AndroidSourceSet {

}
