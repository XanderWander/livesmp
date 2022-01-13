package nl.xanderwander.livesmp.commands.sleep

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent

class EnterBedEvent: Listener {

    @EventHandler
    fun onSleep(event: PlayerBedEnterEvent) {
        event.player.performCommand("slapen")
    }

}