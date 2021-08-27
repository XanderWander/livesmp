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