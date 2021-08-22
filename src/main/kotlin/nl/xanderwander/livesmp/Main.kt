package nl.xanderwander.livesmp

import nl.xanderwander.livesmp.core.Tablist
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    override fun onEnable() {

        instance = this

        registerCommands()
        registerEvents()

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {
        logger.info("${description.name} has been disabled.")
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(Tablist(), this)
    }

    private fun registerCommands() {
        //getCommand("")?.setExecutor()
    }

}