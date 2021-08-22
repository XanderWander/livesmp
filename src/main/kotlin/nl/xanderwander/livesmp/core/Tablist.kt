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

            val mul =   if (t == 1) "${ChatColor.GRAY} Er is op dit moment ${ChatColor.of("#ff0000")}1${ChatColor.GRAY} speler online. \n"
                        else "${ChatColor.GRAY} Er zijn op dit moment ${ChatColor.of("#ff0000")}${t}${ChatColor.GRAY} spelers online. \n"

            p.setPlayerListHeaderFooter(
                "\n" +
                        "${ChatColor.of("#ff0000")}${ChatColor.BOLD}Live${ChatColor.WHITE}${ChatColor.BOLD}SMP\n" +
                        "",
                "\n" +
                        mul +
                        "\n" +
                        "${ChatColor.of("#ff0000")}${ChatColor.UNDERLINE}LiveSMP.com\n" +
                        "")

        }
    }

}
