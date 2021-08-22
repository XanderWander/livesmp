package nl.xanderwander.livesmp.core

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
            p.setPlayerListHeaderFooter("test", "test2")
        }
    }

}