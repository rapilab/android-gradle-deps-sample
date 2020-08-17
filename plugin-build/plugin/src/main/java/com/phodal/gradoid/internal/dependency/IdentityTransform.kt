package com.phodal.gradoid.internal.dependency

import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Classpath
import java.io.IOException

abstract class IdentityTransform : TransformAction<GenericTransformParameters> {

    @get:Classpath
    @get:InputArtifact
    abstract val inputArtifact: Provider<FileSystemLocation>

    override fun transform(transformOutputs: TransformOutputs) {
        val input = inputArtifact.get().asFile
        when {
            input.isDirectory -> transformOutputs.dir(input)
            input.isFile -> transformOutputs.file(input)
            else -> throw IOException("Expecting a file or a directory: ${input.canonicalPath}")
        }
    }
}
