package io.github.monun.sample.internal

import io.github.monun.sample.LibraryLoader
import io.github.monun.sample.Sample

class SampleImpl: Sample {
    private val version = LibraryLoader.loadNMS(Version::class.java)

    override fun printCoreMessage() {
        println("This is core, version = ${version.value}")
    }
}

interface Version {
    val value: String
}