package com.phodal.gradoid

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class ExamplePluginTest {

    @Test
    fun `plugin is applied correctly to the project`() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("com.phodal.gradoid")

        assert(project.tasks.getByName("phodalJavaCompile") is PhodalCompile)
    }
}
