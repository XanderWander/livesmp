package nl.xanderwander.livesmp.events

import net.md_5.bungee.api.ChatColor
import nl.xanderwander.livesmp.utils.ItemUtils
import org.bukkit.Material
import org.bukkit.block.CreatureSpawner
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class BreakPlace: Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val p = event.player
        if (p.inventory.itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
            val b = event.block
            if (b.type == Material.SPAWNER) {
                val type = (b.state as CreatureSpawner).spawnedType
                val i = ItemUtils.newItem(Material.SPAWNER, "${ChatColor.YELLOW}$type Spawner")
                p.world.dropItem(b.location, i)
            }
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val p = event.player
        val tool = p.inventory.itemInMainHand
        if (tool.type == Material.SPAWNER) {
            val tn = tool.itemMeta!!.displayName
            val list = arrayListOf("BLAZE", "SKELETON", "SPIDER", "ZOMBIE", "SILVERFISH", "MAGMA_CUBE", "CAVE_SPIDER")
            for (item in list) {
                if (tn.contains("${ChatColor.YELLOW}$item ")) {
                    val e = EntityType.valueOf(item)
                    val bs = event.block.state
                    (bs as CreatureSpawner).spawnedType = e
                    bs.update(true, false)
                    return
                }
            }
            event.isCancelled = true
            p.sendMessage("${ChatColor.RED}Illigal spawner")
        }
    }

}