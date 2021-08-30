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

        //First register player
        PlayerManager.addPlayer(e.player)
        StaticModule.updateTab()

        StaticModule.setResourcePack(e.player)
        StaticModule.joinInfo(e.player)
        PlayerManager.setFlag(e.player, WorldChange.worldNameFlag(e.player.world))

        e.joinMessage = "§a+ §7${e.player.name}"
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {

        Main.instance.adminMode.forceExit(e.player)

        //Last unregister player
        PlayerManager.remPlayer(e.player)
        StaticModule.updateTab()

        e.quitMessage = "§c- §7${e.player.name}"
    }

}