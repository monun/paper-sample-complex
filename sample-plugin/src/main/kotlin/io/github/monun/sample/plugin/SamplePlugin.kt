package io.github.monun.sample.plugin

import io.github.monun.sample.Sample
import org.bukkit.plugin.java.JavaPlugin

class SamplePlugin: JavaPlugin() {
    override fun onEnable() {
        Sample.printCoreMessage()
    }
}