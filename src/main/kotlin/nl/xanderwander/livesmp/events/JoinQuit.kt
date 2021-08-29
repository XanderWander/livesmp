package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.player.PlayerManager
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuit: Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        PlayerManager.addPlayer(e.player)
        StaticModule.updateTab()
        StaticModule.setResourcePack(e.player)
        StaticModule.joinInfo(e.player)
        PlayerManager.setFlag(e.player, WorldChange.worldNameFlag(e.player.world))

        e.joinMessage = "§a+ §7${e.player.name}"
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        PlayerManager.remPlayer(e.player)
        RunnableHelper.runLater(1L) { StaticModule.updateTab() }
        Main.instance.adminMode.forceExit(e.player)
        StaticModule.updateTab()

        e.quitMessage = "§c- §7${e.player.name}"
    }

}