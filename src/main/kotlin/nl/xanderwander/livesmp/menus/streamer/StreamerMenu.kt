package nl.xanderwander.livesmp.menus.streamer

import nl.xanderwander.livesmp.menus.MenuManager
import nl.xanderwander.livesmp.utils.ItemUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

class StreamerMenu {

    private val menu = MenuManager(3*9, "test")

    init {

        menu.menu.setItem(0, ItemUtils.newItem(Material.STONE, "Thomas je stinkt"))

        menu.onClick {
            if (it.clickedInventory == menu.menu) {
                Bukkit.broadcastMessage("${it.whoClicked.name} Clicked on the streamer menu")
                it.isCancelled = true
            } else {
                Bukkit.broadcastMessage("${it.whoClicked.name} Clicked outside the streamer menu")
                if (it.isShiftClick) {
                    it.isCancelled = true
                }
            }
        }

        menu.onDrag {
            it.isCancelled = true
        }

    }

    fun openTo(player: Player) {
        player.openInventory(menu.menu)
    }

}