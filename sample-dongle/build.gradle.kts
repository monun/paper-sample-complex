import io.papermc.paperweight.tasks.RemapJar

plugins {
    id("io.papermc.paperweight.userdev") version "1.3.8" apply false
}

val projectAPI = project(":${rootProject.name}-api")
val projectCORE = project(":${rootProject.name}-core")

subprojects {
    // net.minecraft.server 프로젝트의 이름은 반드시 v로 시작 [v1.19]
    apply(plugin = "io.papermc.paperweight.userdev")
    dependencies {
        implementation(projectAPI)
        implementation(projectCORE)
        paperDevBundle("${name.substring(1)}-R0.1-SNAPSHOT")
    }
}

projectCORE.tasks {
    named<Jar>("coreDevJar") {
        from(subprojects.map { it.sourceSets["main"].output })
    }

    named<Jar>("coreReobfJar") {
        subprojects.map { it.tasks.named("reobfJar").get() as RemapJar }.onEach {
            from(zipTree(it.outputJar))
        }.let {
            dependsOn(it)
        }
    }

    named<Jar>("sourcesJar") {
        from(subprojects.map { it.sourceSets["main"].allSource })
    }
}





