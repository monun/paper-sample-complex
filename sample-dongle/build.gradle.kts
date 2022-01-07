plugins {
    id("io.papermc.paperweight.userdev") version "1.3.3-SNAPSHOT" apply false
}

val api = project(":${rootProject.name}-api")
val core = project(":${rootProject.name}-core")

tasks {
    jar {
        also { dongleJar ->
            subprojects.forEach { subproject ->
                subproject.tasks.named("reobfJar") {
                    dongleJar.dependsOn(this)
                    dongleJar.from(zipTree((this as io.papermc.paperweight.tasks.RemapJar).outputJar))
                }
            }
        }
    }
}

subprojects {
    apply(plugin = "io.papermc.paperweight.userdev")

    dependencies {
        implementation(api)
        implementation(core)
        paperDevBundle("${project.name.substring(1)}-R0.1-SNAPSHOT")
    }
}



