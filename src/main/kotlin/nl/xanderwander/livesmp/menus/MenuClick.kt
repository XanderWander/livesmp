package nl.xanderwander.livesmp.menus

import nl.xanderwander.livesmp.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory

class MenuClick: Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (Main.instance.listeners.containsKey(event.inventory)) {
            val f = Main.instance.listeners[event.inventory]
            f?.invoke(event)
        }
    }

    @EventHandler
    fun onInventoryDrag(event: InventoryDragEvent) {
        if (Main.instance.dragListeners.containsKey(event.inventory)) {
            val f = Main.instance.dragListeners[event.inventory]
            f?.invoke(event)
        }
    }

    fun register(inv: Inventory, f: (event: InventoryClickEvent) -> Unit) {
        Main.instance.listeners[inv] = f
    }

    fun remove(inv: Inventory) {
        Main.instance.listeners.remove(inv)
    }

    fun registerDrag(inv: Inventory, f: (event: InventoryDragEvent) -> Unit) {
        Main.instance.dragListeners[inv] = f
    }

    fun removeDrag(inv: Inventory) {
        Main.instance.dragListeners.remove(inv)
    }

}