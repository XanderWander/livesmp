package nl.xanderwander.livesmp.trails

import nl.xanderwander.livesmp.utils.RunnableUtils
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class TrailItem(private val item: ItemStack) {

    fun spawn(loc: Location) {
        RunnableUtils.doSync {
            iSpawn(loc)
        }
    }

    private fun iSpawn(loc: Location) {
        val world = loc.world ?: return
        val item = world.dropItem(loc, item)
        item.pickupDelay = Int.MAX_VALUE
        RunnableUtils.runLater(20L) {
            item.remove()
        }
    }

}