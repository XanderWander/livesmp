package nl.xanderwander.livesmp.modules

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.commands.adminmode.TabCompleteEvent
import nl.xanderwander.livesmp.chat.ChatEvent
import nl.xanderwander.livesmp.commands.*
import nl.xanderwander.livesmp.commands.dev.Debug
import nl.xanderwander.livesmp.events.*
import nl.xanderwander.livesmp.playerflags.WorldChangeEvent
import nl.xanderwander.livesmp.commands.sleep.EnterBedEvent
import nl.xanderwander.livesmp.commands.sleep.SleepCommand
import nl.xanderwander.livesmp.itemprotect.GameModeEvent
import nl.xanderwander.livesmp.trails.TrailCommand
import org.bukkit.Bukkit

class RegisterModule {

    fun register(instance: Main) {
        registerEvents(instance)
        registerCommands(instance)
    }

    private fun registerEvents(instance: Main) {
        // package nl.xanderwander.livesmp.events
        Bukkit.getPluginManager().registerEvents(ElytraArmor(), instance)
        Bukkit.getPluginManager().registerEvents(JoinQuit(), instance)
        Bukkit.getPluginManager().registerEvents(MOTD(), instance)
        Bukkit.getPluginManager().registerEvents(SilkSpawner(), instance)
        Bukkit.getPluginManager().registerEvents(SpawnProtect(), instance)
        Bukkit.getPluginManager().registerEvents(ToolProtect(), instance)
        Bukkit.getPluginManager().registerEvents(WorldProtect(), instance)

        // other events
        Bukkit.getPluginManager().registerEvents(ChatEvent(), instance) // package nl.xanderwander.livesmp.chat
        Bukkit.getPluginManager().registerEvents(TabCompleteEvent(), instance) // package nl.xanderwander.livesmp.commands.adminmode
        Bukkit.getPluginManager().registerEvents(EnterBedEvent(), instance) // package nl.xanderwander.livesmp.commands.sleep
        Bukkit.getPluginManager().registerEvents(GameModeEvent(), instance) // package nl.xanderwander.livesmp.itemprotect
        Bukkit.getPluginManager().registerEvents(WorldChangeEvent(), instance) // package nl.xanderwander.livesmp.playerflags

    }

    private fun registerCommands(instance: Main) {
        instance.getCommand("resourcepack")?.setExecutor(Resourcepack())
        instance.getCommand("rp")?.setExecutor(Resourcepack())
        instance.getCommand("rrp")?.setExecutor(Resourcepack())
        instance.getCommand("slapen")?.setExecutor(SleepCommand())
        instance.getCommand("adminmode")?.setExecutor(instance.adminModeCommand)
        instance.getCommand("logdebug")?.setExecutor(Debug())
        instance.getCommand("spawn")?.setExecutor(Spawn())
        instance.getCommand("help")?.setExecutor(Help())
        instance.getCommand("regels")?.setExecutor(Help())
        instance.getCommand("discord")?.setExecutor(Help())
        instance.getCommand("showitem")?.setExecutor(ShowItem())
        instance.getCommand("trail")?.setExecutor(TrailCommand())
    }

}