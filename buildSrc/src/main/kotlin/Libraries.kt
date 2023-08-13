import org.gradle.api.artifacts.Dependency

object Libraries {
    object Kotlin {
        const val Version = "1.8.22"
    }

    object Dokka {
        const val Version = "1.8.20"
    }

    object Paper {
        const val Version = "1.20.1"
    }

//    private val _dependencies = linkedSetOf<Dependency>()
//    val dependencies: Set<Dependency> = _dependencies
//
//    fun add(dependency: Dependency) {
//        if (_dependencies.none { it.contentEquals(dependency) }) {
//            _dependencies.add(dependency)
//        }
//    }
//
//    override fun toString(): String {
//        return _dependencies.joinToString(separator = "\n  ") { dependency ->
//            val group = dependency.group ?: error("group is null")
//            var name = dependency.name ?: error("name is null")
//            var version = dependency.version
//
//            if (group == "org.jetbrains.kotlin") {
//                if (version == null) version = Kotlin.Version
//            } else if (group == "io.github.monun") { // If you don't like it, delete it. It's for my library.
//                if (name.endsWith("-api")) name = name.removeSuffix("api") + "core"
//            }
//
//            requireNotNull(version) { "version is null" }
//            require(version != "latest.release") { "version is latest.release" }
//
//            "- $group:$name:$version"
//        }
//    }
}

//fun Dependency?.library() {
//    if (this != null) {
//        Libraries.add(this)
//    }
//}
