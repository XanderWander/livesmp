package nl.xanderwander.livesmp.events

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class PlayerSleep: Listener {

    @EventHandler
    fun onSleep(event: PlayerBedEnterEvent) {
        event.player.performCommand("slapen")
    }

}