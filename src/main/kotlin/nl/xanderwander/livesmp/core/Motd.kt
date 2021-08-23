package nl.xanderwander.livesmp.core

import net.md_5.bungee.api.ChatColor
import nl.xanderwander.livesmp.utils.StringUtils
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerListPingEvent

open class Motd: Listener {

    @EventHandler
    open fun onServerPing(event: ServerListPingEvent) {

        val t = Bukkit.getOnlinePlayers().size
        val red = ChatColor.of("#ff0000")
        val mul =   if (t != 1) listOf("zijn", "spelers") else listOf("is", "speler")

        event.motd =    "${StringUtils.spaces(25)}${red}§l Live${"§f§l"}SMP \n" +
                        "${StringUtils.spaces( 7)}§7 Er ${mul[0]} op dit moment ${red}$t §7${mul[1]} online. \n"
    }

}