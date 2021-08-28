package nl.xanderwander.livesmp.modules

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.commands.Resourcepack
import nl.xanderwander.livesmp.commands.Sleep
import nl.xanderwander.livesmp.events.*
import org.bukkit.Bukkit

class RegisterModule {

    fun register(instance: Main) {
        registerEvents(instance)
        registerCommands(instance)
    }

    private fun registerEvents(instance: Main) {
        Bukkit.getPluginManager().registerEvents(BreakPlace(), instance)
        Bukkit.getPluginManager().registerEvents(JoinQuit(), instance)
        Bukkit.getPluginManager().registerEvents(ChatEvent(), instance)
        Bukkit.getPluginManager().registerEvents(EntityDeath(), instance)
        Bukkit.getPluginManager().registerEvents(PlayerSleep(), instance)
        Bukkit.getPluginManager().registerEvents(ServerPing(), instance)
        Bukkit.getPluginManager().registerEvents(TabComplete(), instance)
        Bukkit.getPluginManager().registerEvents(WorldChange(), instance)
        Bukkit.getPluginManager().registerEvents(EntityChangeBlock(), instance)
        Bukkit.getPluginManager().registerEvents(PlayerInteractEvent(), instance)
    }

    private fun registerCommands(instance: Main) {
        instance.getCommand("resourcepack")?.setExecutor(Resourcepack())
        instance.getCommand("rp")?.setExecutor(Resourcepack())
        instance.getCommand("rrp")?.setExecutor(Resourcepack())
        instance.getCommand("slapen")?.setExecutor(Sleep())
        instance.getCommand("adminmode")?.setExecutor(instance.adminMode)
    }

}