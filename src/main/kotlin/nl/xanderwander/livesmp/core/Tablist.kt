package nl.xanderwander.livesmp.core

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

open class Tablist: Listener {

    @EventHandler
    open fun onJoin(event: PlayerJoinEvent) {
        updateTab()
    }

    private fun updateTab() {
        for (p: Player in Bukkit.getOnlinePlayers()) {

            p.setPlayerListHeaderFooter(
                "\n" +
                        "${ChatColor.of("#ff0000")}${ChatColor.BOLD}Live${ChatColor.WHITE}${ChatColor.BOLD}SMP\n" +
                        "",
                "\n" +
                        "${ChatColor.GRAY}Er zijn op dit moment ${ChatColor.of("#ff0000")}${Bukkit.getOnlinePlayers().size}${ChatColor.GRAY} mensen online. \n" +
                        "\n" +
                        "${ChatColor.of("#ff0000")}${ChatColor.UNDERLINE}LiveSMP.com\n" +
                        "")
        }
    }

}
