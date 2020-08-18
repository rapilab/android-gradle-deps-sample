package com.phodal.gradoid.com.phodal.gradoid

import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.phodal.gradoid.AppExtension
import com.phodal.gradoid.AppPlugin
import com.phodal.gradoid.PhodalCompile
import groovy.util.Eval
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.nio.file.Files

open class SamplePluginTest {
    private lateinit var extension: AppExtension
    private lateinit var plugin: AppPlugin

    @Rule
    @JvmField
    var projectDirectory = TemporaryFolder()

    @Test
    @Throws(Exception::class)
    fun `build sample projects`() {
        val project = buildProject()

        plugin = project!!.plugins.getPlugin(AppPlugin::class.java)
        extension = project.extensions.getByType(AppExtension::class.java)

        assert(project.tasks.getByName("phodalJavaCompile") is PhodalCompile)
    }

    private fun buildProject(): Project? {
        val projectDir = projectDirectory.newFolder("project").toPath()
        val projectName = "test"

        val manifest = projectDir.resolve("config.json")

        val content: String = "{}"
        Files.createDirectories(manifest.parent)
        Files.write(manifest, ImmutableList.of(content))

        val projectBuilder = ProjectBuilder.builder()
                .withProjectDir(projectDir.toFile())
                .withName(projectName)

        val project = projectBuilder.build()

        project.apply(ImmutableMap.of<String, String?>("plugin", "com.phodal.gradoid"))

        return project
    }
}