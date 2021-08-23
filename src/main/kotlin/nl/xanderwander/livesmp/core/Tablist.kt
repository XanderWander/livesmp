package nl.xanderwander.livesmp.core

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

open class Tablist: Listener {

    @EventHandler
    open fun onJoin(event: PlayerJoinEvent) {
        updateTab()
    }

    @EventHandler
    open fun onQuit(event: PlayerQuitEvent) {
        updateTab()
    }

    private fun updateTab() {
        for (p: Player in Bukkit.getOnlinePlayers()) {

            val t = Bukkit.getOnlinePlayers().size
            val red = ChatColor.of("#ff0000")
            val mul =   if (t != 1) listOf("zijn", "spelers") else listOf("is", "speler")

            p.setPlayerListHeaderFooter(
                "\n" +
                        "${red}§l Live${"§f§l"}SMP \n" +
                        "",
                "\n" +
                        "§7 Er ${mul[0]} op dit moment ${red}$t §7${mul[1]} online. \n" +
                        "\n" +
                        "${red}§nLiveSMP.com\n" +
                        "")

        }
    }

}
