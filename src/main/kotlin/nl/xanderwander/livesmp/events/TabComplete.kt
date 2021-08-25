package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.TabCompleteEvent

class TabComplete: Listener {

    @EventHandler
    fun onTabComplete(event: TabCompleteEvent) {

        if (event.sender is Player) {
            if (Main.instance.playerModule.isVisible(event.sender as Player)) {
                val completions = event.completions
                val changed = arrayListOf<String>()
                for (completion in completions) {
                    for (player in PlayerModule.hiddenPlayers) {
                        if (player.name == completion) {
                            continue
                        }
                    }
                    changed.add(completion)
                }
                event.completions = changed
            }
        }

    }

}