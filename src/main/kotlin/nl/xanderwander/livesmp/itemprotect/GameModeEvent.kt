package nl.xanderwander.livesmp.itemprotect

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.utils.send
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerGameModeChangeEvent

class GameModeEvent: Listener {

    @EventHandler
    fun onGameModeChange(event: PlayerGameModeChangeEvent) {

        if (event.player.gameMode == event.newGameMode) return

        if (event.newGameMode == GameMode.CREATIVE) {
            Main.inventoryStorage.storeInventory(event.player)
            event.player.send("Je survival inventory is opgeslagen.")
        }

        if (event.player.gameMode == GameMode.CREATIVE) {
            if (Main.inventoryStorage.hasInventoryStored(event.player)) {
                Main.inventoryStorage.loadInventory(event.player)
                event.player.send("Je survival inventory is ingeladen.")
            }
        }

    }

}