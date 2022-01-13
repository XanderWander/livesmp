package nl.xanderwander.livesmp.events

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.player.PlayerInteractEvent

class WorldProtect: Listener {

    @EventHandler
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        if (event.entityType == EntityType.ENDERMAN) {
            event.isCancelled = true
        }
    }

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

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val drops = event.drops
        for (drop in drops) {
            if (drop.type == Material.WHITE_BANNER) {
                val killer = event.entity.killer
                if (killer != null) {
                    val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft("adventure/voluntary_exile"))
                    val progress = killer.getAdvancementProgress(advancement ?: return)
                    val criteria = progress.remainingCriteria
                    for (criterion in criteria) {
                        progress.awardCriteria(criterion)
                    }
                }
            }
        }
    }

}