import org.gradle.api.Project

private fun Project.getSubprojectByName(name: String) = project(":${rootProject.name}-$name")

val Project.projectApi
    get() = getSubprojectByName("api")

val Project.projectCore
    get() = getSubprojectByName("core")

val Project.projectPlugin
    get() = getSubprojectByName("plugin")
