package nl.xanderwander.livesmp

import net.luckperms.api.LuckPerms
import nl.xanderwander.livesmp.chat.ChatEvent
import nl.xanderwander.livesmp.core.Motd
import nl.xanderwander.livesmp.core.Resourcepack
import org.bukkit.Bukkit
import nl.xanderwander.livesmp.core.Tablist
import nl.xanderwander.livesmp.data.ConfigManager
import nl.xanderwander.livesmp.luckperms.LuckPermsHook
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    var luckPerms: LuckPerms? = null

    lateinit var configManager: ConfigManager
    lateinit var luckPermsHook: LuckPermsHook

    override fun onEnable() {

        instance = this

        registerCommands()
        registerEvents()
        loadLuckPerms()

        configManager = ConfigManager()
        luckPermsHook = LuckPermsHook()

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {
        logger.info("${description.name} has been disabled.")
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(ChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(Motd(), this)
        Bukkit.getPluginManager().registerEvents(Resourcepack(), this)
        Bukkit.getPluginManager().registerEvents(Tablist(), this)
    }

    private fun registerCommands() {
        //getCommand("")?.setExecutor()
    }

    private fun loadLuckPerms() {

        val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (provider != null) {
            luckPerms = provider.provider
        }

    }

}