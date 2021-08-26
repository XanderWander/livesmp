package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.player.PlayerManager
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuit: Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        StaticModule.updateTab()
        StaticModule.setResourcePack(event.player)
        StaticModule.joinInfo(event.player)
        PlayerManager.addPlayer(event.player)

        event.joinMessage = "§a+ §7${event.player.name}"
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        PlayerManager.remPlayer(event.player)
        RunnableHelper.runLater(1L) { StaticModule.updateTab() }

        event.quitMessage = "§c- §7${event.player.name}"
    }

}