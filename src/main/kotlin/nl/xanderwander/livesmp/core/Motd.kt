package nl.xanderwander.livesmp.core

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent

open class Motd: Listener {

    @EventHandler
    open fun onServerPing(event: ServerListPingEvent) {
        val t = Bukkit.getOnlinePlayers().size
        if (t == 1) {
            event.motd = "${spaces(25)}${ChatColor.of("#ff0000")}${ChatColor.BOLD}Live${ChatColor.WHITE}${ChatColor.BOLD}SMP\n" +
            "${spaces(7)}${ChatColor.GRAY}Er is op dit moment ${ChatColor.of("#ff0000")}1${ChatColor.GRAY} speler online. \n"
        }
        else {
            event.motd = "${spaces(25)}${ChatColor.of("#ff0000")}${ChatColor.BOLD}Live${ChatColor.WHITE}${ChatColor.BOLD}SMP\n" +
            "${spaces(7)}${ChatColor.GRAY}Er zijn op dit moment ${ChatColor.of("#ff0000")}${t}${ChatColor.GRAY} spelers online. \n"
        }
    }

    fun spaces(c: Int): String {
        var v = ""
        for (i in 1 until c) {
            v += " "
        }
        return v
    }

}