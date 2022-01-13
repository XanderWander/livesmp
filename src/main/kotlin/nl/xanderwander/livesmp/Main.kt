package nl.xanderwander.livesmp

import nl.xanderwander.livesmp.chat.LuckPermsHook
import nl.xanderwander.livesmp.commands.adminmode.AdminModeCommand
import nl.xanderwander.livesmp.modules.RegisterModule
import nl.xanderwander.livesmp.modules.SleepModule
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.modules.PlayerModule
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
        lateinit var luckPermsHook: LuckPermsHook
        lateinit var version: String
    }

    private val registerModule = RegisterModule()

    val sleepModule = SleepModule()
    val adminModeCommand = AdminModeCommand()

    override fun onEnable() {

        instance = this
        version = server.javaClass.getPackage().name.split(".")[3]
        luckPermsHook = LuckPermsHook()

        registerModule.register(this)
        sleepModule.runTaskTimer(this, 0L, 1L)

        PlayerModule.initialize()
        StaticModule.updateTab()

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {

        for (player in PlayerModule.all()) {
            adminModeCommand.forceExit(player, true)
        }

        sleepModule.cancel()
        sleepModule.destroy()
        PlayerModule.destroy()

        logger.info("${description.name} has been disabled.")
    }

}