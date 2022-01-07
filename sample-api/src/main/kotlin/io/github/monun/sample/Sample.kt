package io.github.monun.sample

interface Sample {
    companion object: Sample by LibraryLoader.loadImplement(Sample::class.java)

    fun printCoreMessage()
}