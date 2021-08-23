package nl.xanderwander.livesmp.menus

import nl.xanderwander.livesmp.Main
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent

class MenuManager(
    size: Int,
    name: String
) {

    val menu = Bukkit.createInventory(null, size, name)
    private var onClick: (event: InventoryClickEvent) -> Unit = { this.default() }
    private var onDrag: (event: InventoryDragEvent) -> Unit = { this.default() }

    fun onClick(f: (event: InventoryClickEvent) -> Unit) {
        onClick = f

        Main.instance.menuClick.remove(menu)
        Main.instance.menuClick.register(menu, onClick)
    }

    fun onDrag(f: (event: InventoryDragEvent) -> Unit) {
        onDrag = f
        Main.instance.menuClick.removeDrag(menu)
        Main.instance.menuClick.registerDrag(menu, onDrag)
    }

    private fun default() {}

}
