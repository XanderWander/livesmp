package nl.xanderwander.livesmp.commands.adminmode

import nl.xanderwander.livesmp.playerflags.PlayerFlag
import nl.xanderwander.livesmp.modules.PlayerModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.TabCompleteEvent

class TabCompleteEvent: Listener {

    @EventHandler
    fun onTabComplete(event: TabCompleteEvent) {

        if (event.sender is Player) {
            if (!PlayerModule.getFlag(event.sender as Player, PlayerFlag.IS_HIDDEN)) {
                val completions = event.completions
                val changed = arrayListOf<String>()
                for (completion in completions) {
                    for (player in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
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