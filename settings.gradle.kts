rootProject.name = "sample"

val api = "${rootProject.name}-api"
val core = "${rootProject.name}-core"
val plugin = "${rootProject.name}-plugin"

include(api, core, plugin)

val dongle = "${rootProject.name}-dongle"
val dongleFile = file(dongle)
if (dongleFile.exists()) {
    include(dongle)
    // load nms
    dongleFile.listFiles()?.filter {
        it.isDirectory && it.name.startsWith("v")
    }?.forEach { file ->
        include(":$dongle:${file.name}")
    }

    pluginManagement {
        repositories {
            gradlePluginPortal()
            maven("https://papermc.io/repo/repository/maven-public/")
        }
    }
}

val publish = "${rootProject.name}-publish"
val publishFile = file(publish)
if (publishFile.exists()) {
    include(publish)
}