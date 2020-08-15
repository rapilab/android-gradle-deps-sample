@file:JvmName("Aapt2Daemon")
package com.phodal.gradoid.internal.services

import org.gradle.api.Project
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import java.util.*

private val AAPT2_DAEMON_BUILD_SERVICE_NAME = "aapt2-daemon-build-service" + UUID.randomUUID().toString()

fun registerAapt2DaemonBuildService(project: Project) {
    project.gradle.sharedServices.registerIfAbsent(
        AAPT2_DAEMON_BUILD_SERVICE_NAME,
        Aapt2DaemonBuildService::class.java
    ) {}
}

abstract class Aapt2DaemonBuildService : BuildService<BuildServiceParameters.None>,
    AutoCloseable {

}

