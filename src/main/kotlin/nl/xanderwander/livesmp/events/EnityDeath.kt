package nl.xanderwander.livesmp.events

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeath: Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val drops = event.drops
        for (drop in drops) {
            if (drop.type == Material.WHITE_BANNER) {
                val advancement = Bukkit.getAdvancement(NamespacedKey.minecraft("adventure/voluntary_exile"))
                val progress = event.entity.killer.getAdvancementProgress(advancement)
                val criterias = progress.remainingCriteria
                for (criteria in criterias) {
                    progress.awardCriteria(criteria)
                }
            }
        }
    }

}