package nl.xanderwander.livesmp.events

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent

class EntityChangeBlock: Listener {

    @EventHandler
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        if (event.entityType == EntityType.ENDERMAN) {
            event.isCancelled = true
        }
    }
}