package nl.xanderwander.livesmp

import net.luckperms.api.LuckPerms
import nl.xanderwander.livesmp.chat.ChatEvent
import nl.xanderwander.livesmp.core.Motd
import nl.xanderwander.livesmp.core.Resourcepack
import org.bukkit.Bukkit
import nl.xanderwander.livesmp.core.Tablist
import nl.xanderwander.livesmp.data.ConfigManager
import nl.xanderwander.livesmp.luckperms.LuckPermsHook
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

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

    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
        return if(it == null) emptyList()
        else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? =
        Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?: resource::class.java.getResourceAsStream(resource)

}