package nl.xanderwander.livesmp

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import nl.xanderwander.livesmp.modules.LuckPermsModule
import nl.xanderwander.livesmp.commands.AdminMode
import nl.xanderwander.livesmp.modules.RegisterModule
import nl.xanderwander.livesmp.modules.SleepModule
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.player.PlayerManager
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
        lateinit var luckPermsModule: LuckPermsModule
    }

    private val registerModule = RegisterModule()

    val sleepModule = SleepModule()
    val adminMode = AdminMode()

    override fun onEnable() {

        instance = this
        luckPermsModule = LuckPermsModule()

        registerModule.register(this)
        sleepModule.runTaskTimer(this, 0L, 1L)

        PlayerManager.initialize()
        StaticModule.updateTab()

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {

        for (player in PlayerManager.all()) {
            adminMode.forceExit(player)
        }

        sleepModule.cancel()
        sleepModule.destroy()
        PlayerManager.destroy()

        logger.info("${description.name} has been disabled.")
    }

}