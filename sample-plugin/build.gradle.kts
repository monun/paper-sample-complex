val projectAPI = project(":${rootProject.name}-api")
val projectCORE = project(":${rootProject.name}-core")

dependencies {
    implementation(projectAPI)
}

val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }
val packageName = rootProject.name.replace("-", "")
extra.set("pluginName", pluginName)
extra.set("packageName", packageName)

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    fun Jar.copyToDebugServer() = copy {
        from(archiveFile)
        val plugins = File(rootDir, ".debug/plugins/")
        into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
    }

    register<Jar>("pluginJar") {
        archiveBaseName.set(pluginName)
        archiveVersion.set("")

        listOf(projectAPI, projectCORE, project).forEach { project ->
            from(project.sourceSets["main"].output)
        }

        findProject(":${rootProject.name}-dongle")?.let { dongleProject ->
            val dongleJar = dongleProject.tasks.jar

            dependsOn(dongleJar)
            from(zipTree(dongleJar.get().archiveFile))
        }

        exclude("test.yml")

        doLast {
            copyToDebugServer()
        }
    }

    findProject(":${rootProject.name}-publish")?.let { publish ->
        register<Jar>("testJar") {
            archiveBaseName.set(pluginName)
            archiveVersion.set("")

            from(project.sourceSets["main"].output)

            exclude("plugin.yml")
            rename("test.yml", "plugin.yml")

            publish.tasks.let { tasks ->
                dependsOn(tasks.named("publishApiPublicationToDebugRepository"))
                dependsOn(tasks.named("publishCorePublicationToDebugRepository"))
            }

            doLast {
                copyToDebugServer()
            }
        }
    }
}
