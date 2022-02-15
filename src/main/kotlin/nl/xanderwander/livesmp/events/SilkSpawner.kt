package nl.xanderwander.livesmp.events

import net.md_5.bungee.api.ChatColor
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.ItemUtils
import nl.xanderwander.livesmp.utils.StringUtils
import nl.xanderwander.livesmp.utils.send
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.CreatureSpawner
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class SilkSpawner: Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val p = event.player
        if (!spawnProtect(event.block.location)) {
            if (p.inventory.itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH)) {
                val b = event.block
                if (b.type == Material.SPAWNER) {
                    val type = (b.state as CreatureSpawner).spawnedType
                    val i = ItemUtils.newItem(Material.SPAWNER, "${ChatColor.YELLOW}${formatTypeAsName(type)} Spawner")
                    p.world.dropItem(b.location, i)
                }
            }
            val list = arrayListOf(Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.ANCIENT_DEBRIS, Material.SPAWNER)

            if (list.contains(event.block.type)) {
                PlayerModule.all().forEach {
                    if (it.hasPermission("adminmode.notify")) {
                        it.send("${p.name} heeft ${event.block.type.name.lowercase().replace("_", " ")} gemined.")
                    }
                }
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
                if (tn.contains("${ChatColor.YELLOW}${item.replace("_", " ")}", true)) {
                    val e = EntityType.valueOf(item)
                    val bs = event.block.state
                    (bs as CreatureSpawner).spawnedType = e
                    bs.update(true, false)
                    return
                }
            }
            event.isCancelled = true
            p.sendMessage("${ChatColor.RED}Illegal spawner")
        }
    }

    private fun formatTypeAsName(type: EntityType): String {
        return StringUtils.capitalizeFirstLetters(type.name.replace("_", " "))
    }

    private fun spawnProtect(loc: Location): Boolean {

        if (loc.world?.name == "world_the_end") return false
        val size = if (loc.world?.name == "world_nether") 10.0 else 75.0
        if (loc.x >= size) return false
        if (loc.x < -size) return false
        if (loc.z >= size) return false
        if (loc.z < -size) return false
        return true

    }
}