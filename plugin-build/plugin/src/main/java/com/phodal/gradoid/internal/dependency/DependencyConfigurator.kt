package com.phodal.gradoid.internal.dependency

import com.phodal.gradoid.AndroidArtifacts
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.transform.TransformSpec
import org.gradle.api.internal.artifacts.ArtifactAttributes

class DependencyConfigurator(val project: Project, val projectName: String) {
    fun configureDependencies() {
        val dependencies: DependencyHandler = project.dependencies
        dependencies.registerTransform(
            IdentityTransform::class.java
        ) { spec: TransformSpec<GenericTransformParameters> ->
            spec.parameters.projectName.set(projectName)
            spec.from.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.AAR.type
            )
            spec.to.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.PROCESSED_AAR.type
            )
        }

        dependencies.registerTransform(
            IdentityTransform::class.java
        ) { spec: TransformSpec<GenericTransformParameters> ->
            spec.parameters.projectName.set(projectName)
            spec.from.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.JAR.type
            )
            spec.to.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.PROCESSED_JAR.type
            )
        }

        dependencies.registerTransform(
            ExtractAarTransform::class.java
        ) { spec: TransformSpec<GenericTransformParameters> ->
            spec.parameters.projectName.set(projectName)
            spec.from.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.PROCESSED_AAR.type
            )
            spec.to.attribute(
                ArtifactAttributes.ARTIFACT_FORMAT,
                AndroidArtifacts.ArtifactType.EXPLODED_AAR.type
            )
        }
    }

}
