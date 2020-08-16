package com.phodal.gradoid

import org.gradle.api.JavaVersion
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

open class PhodalCompile: JavaCompile() {
    override fun compile(inputs: IncrementalTaskInputs) {
        println(JavaVersion.current().isJava8Compatible)
    }
}
