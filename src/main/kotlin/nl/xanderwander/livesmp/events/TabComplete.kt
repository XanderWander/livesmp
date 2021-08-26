package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.TabCompleteEvent

class TabComplete: Listener {

    @EventHandler
    fun onTabComplete(event: TabCompleteEvent) {

        if (event.sender is Player) {
            if (!PlayerManager.getFlag(event.sender as Player, PlayerFlag.IS_HIDDEN)) {
                val completions = event.completions
                val changed = arrayListOf<String>()
                for (completion in completions) {
                    for (player in PlayerManager.match(PlayerFlag.IS_HIDDEN)) {
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