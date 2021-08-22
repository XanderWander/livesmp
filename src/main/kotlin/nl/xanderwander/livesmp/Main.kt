package nl.xanderwander.livesmp

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
        //Bukkit.getPluginManager().registerEvents(, this)
    }

    private fun registerCommands() {
        //getCommand("")?.setExecutor()
    }

}