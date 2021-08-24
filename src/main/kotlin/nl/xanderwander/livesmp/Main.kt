package nl.xanderwander.livesmp

import net.luckperms.api.LuckPerms
import nl.xanderwander.livesmp.chat.ChatEvent
import nl.xanderwander.livesmp.core.Motd
import nl.xanderwander.livesmp.core.Resourcepack
import org.bukkit.Bukkit
import nl.xanderwander.livesmp.core.Tablist
import nl.xanderwander.livesmp.data.ConfigManager
import nl.xanderwander.livesmp.luckperms.LuckPermsHook
import nl.xanderwander.livesmp.menus.MenuClick
import nl.xanderwander.livesmp.commands.MenuCommand
import nl.xanderwander.livesmp.commands.SleepCommand
import nl.xanderwander.livesmp.events.PlayerSleep
import nl.xanderwander.livesmp.events.SilkSpawners
import nl.xanderwander.livesmp.menus.MenuInstances
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.modules.SleepModule
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    lateinit var menuClick: MenuClick
    lateinit var configManager: ConfigManager
    lateinit var luckPermsHook: LuckPermsHook
    lateinit var menuInstances: MenuInstances
    lateinit var sleepModule: SleepModule

    var luckPerms: LuckPerms? = null
    val playerModule = PlayerModule()
    val listeners = hashMapOf<Inventory, (event: InventoryClickEvent) -> Unit>()
    val dragListeners = hashMapOf<Inventory, (event: InventoryDragEvent) -> Unit>()

    override fun onEnable() {

        instance = this

        registerCommands()
        registerEvents()
        loadLuckPerms()

        menuClick = MenuClick()
        configManager = ConfigManager()
        luckPermsHook = LuckPermsHook()
        menuInstances = MenuInstances()
        sleepModule = SleepModule()

        for (player in Bukkit.getOnlinePlayers()) {
            playerModule.register(player)
        }

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {

        sleepModule.cancel()
        sleepModule.destroy()

        logger.info("${description.name} has been disabled.")
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(PlayerModule(), this)
        Bukkit.getPluginManager().registerEvents(ChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(Motd(), this)
        Bukkit.getPluginManager().registerEvents(Resourcepack(), this)
        Bukkit.getPluginManager().registerEvents(Tablist(), this)
        Bukkit.getPluginManager().registerEvents(MenuClick(), this)
        Bukkit.getPluginManager().registerEvents(PlayerSleep(), this)
        Bukkit.getPluginManager().registerEvents(SilkSpawners(), this)
    }

    private fun registerCommands() {
        getCommand("menu")?.setExecutor(MenuCommand())
        getCommand("resourcepack")?.setExecutor(Resourcepack())
        getCommand("rp")?.setExecutor(Resourcepack())
        getCommand("rrp")?.setExecutor(Resourcepack())
        getCommand("slapen")?.setExecutor(SleepCommand())
    }

    private fun loadLuckPerms() {

        val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (provider != null) {
            luckPerms = provider.provider
        }

    }

    fun resetConfig() {

//        RunnableHelper.runLater(40L) {
//            val content = Main::class.java.getResource("/config.yml").readText()
//            val file = configManager.safeFile("plugins/LiveSMP", "config.yml")
//            file.writeText(content)
//        }
    }

}