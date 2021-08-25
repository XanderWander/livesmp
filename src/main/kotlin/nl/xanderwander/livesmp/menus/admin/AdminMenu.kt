package nl.xanderwander.livesmp.menus.admin

import nl.xanderwander.livesmp.menus.MenuManager
import nl.xanderwander.livesmp.utils.ItemUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player

class AdminMenu {

    private val menu = MenuManager(3*9, "§cAdmin Menu")

    init {

        menu.menu.setItem(13, ItemUtils.newItem(Material.ENDER_PEARL, "§cAdmin Mode"))

        menu.onClick {

            if (it.clickedInventory == menu.menu) {
                it.isCancelled = true
                if (it.slot == 13) {
                    (it.whoClicked as Player).performCommand("adminmode")
                    it.whoClicked.closeInventory()
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