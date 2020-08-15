package com.phodal.gradoid.internal.dependency

import com.phodal.gradoid.internal.api.DefaultAndroidSourceSet
import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.Project

class AndroidSourceSetFactory(val project: Project): NamedDomainObjectFactory<AndroidSourceSet> {
    override fun create(name: String): AndroidSourceSet {
        return project.objects.newInstance(DefaultAndroidSourceSet::class.java, name, project)
    }
}
