package io.github.teamuselessplugin.sample.plugin

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("Hello, world!")
    }
}