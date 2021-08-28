package nl.xanderwander.livesmp.events

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractEvent: Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val block = event.clickedBlock
        if (block != null) {
            if (block.type == Material.FARMLAND) {
                if (event.action == Action.PHYSICAL) {
                    event.isCancelled = true
                }
            }
        }
    }

}