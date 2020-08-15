package com.phodal.gradoid.internal.dependency

import org.gradle.api.NamedDomainObjectFactory
import org.gradle.api.Project

class AndroidSourceSetFactory(val project: Project): NamedDomainObjectFactory<AndroidSourceSet> {
    override fun create(name: String): AndroidSourceSet {
        return project.objects.newInstance(DefaultAndroidSourceSet::class.java, name, project)
    }

}
