package com.phodal.gradoid.internal.dependency

import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal

interface GenericTransformParameters: TransformParameters {
    @get:Internal
    val projectName: Property<String>
}
