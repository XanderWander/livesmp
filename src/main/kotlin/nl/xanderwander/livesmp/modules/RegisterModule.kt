package nl.xanderwander.livesmp.modules

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.commands.*
import nl.xanderwander.livesmp.events.*
import org.bukkit.Bukkit

class RegisterModule {

    fun register(instance: Main) {
        registerEvents(instance)
        registerCommands(instance)
    }

    private fun registerEvents(instance: Main) {
        Bukkit.getPluginManager().registerEvents(BreakPlace(), instance)
        Bukkit.getPluginManager().registerEvents(ChatEvent(), instance)
        Bukkit.getPluginManager().registerEvents(EntityChangeBlock(), instance)
        Bukkit.getPluginManager().registerEvents(EntityDeath(), instance)
        Bukkit.getPluginManager().registerEvents(JoinQuit(), instance)
        Bukkit.getPluginManager().registerEvents(PlayerInteractEvent(), instance)
        Bukkit.getPluginManager().registerEvents(PlayerSleep(), instance)
        Bukkit.getPluginManager().registerEvents(PrepareAnvil(), instance)
        Bukkit.getPluginManager().registerEvents(ServerPing(), instance)
        Bukkit.getPluginManager().registerEvents(SpawnProtect(), instance)
        Bukkit.getPluginManager().registerEvents(TabComplete(), instance)
        Bukkit.getPluginManager().registerEvents(ToolBreak(), instance)
        Bukkit.getPluginManager().registerEvents(WorldChange(), instance)
    }

    private fun registerCommands(instance: Main) {
        instance.getCommand("resourcepack")?.setExecutor(Resourcepack())
        instance.getCommand("rp")?.setExecutor(Resourcepack())
        instance.getCommand("rrp")?.setExecutor(Resourcepack())
        instance.getCommand("slapen")?.setExecutor(Sleep())
        instance.getCommand("adminmode")?.setExecutor(instance.adminMode)
        instance.getCommand("logdebug")?.setExecutor(Debug())
        instance.getCommand("spawn")?.setExecutor(Spawn())
        instance.getCommand("help")?.setExecutor(Help())
        instance.getCommand("regels")?.setExecutor(Help())
        instance.getCommand("discord")?.setExecutor(Help())
    }

}