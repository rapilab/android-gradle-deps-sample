@file:JvmName("Aapt2Workers")

package com.phodal.gradoid.internal.services

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.util.*

private val AAPT2_WORKERS_BUILD_SERVICE_NAME = "aapt2-workers-build-service" + UUID.randomUUID().toString()

fun registerAapt2WorkersBuildService(project: Project) {
    project.gradle.sharedServices.registerIfAbsent(
        AAPT2_WORKERS_BUILD_SERVICE_NAME,
        Aapt2WorkersBuildService::class.java
    ) {}
}

abstract class Aapt2WorkersBuildService : BuildService<Aapt2WorkersBuildService.Params>,
    AutoCloseable {
    interface Params : BuildServiceParameters {
        val aapt2ThreadPoolSize: Property<Int>
    }

}

