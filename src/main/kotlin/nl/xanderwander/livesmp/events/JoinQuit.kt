package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.playerflags.WorldChangeEvent
import nl.xanderwander.livesmp.web.WebLoader
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuit: Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {

        //First register player
        PlayerModule.addPlayer(e.player)
        StaticModule.updateTab()

        WebLoader.setResourcePack(e.player)
        StaticModule.joinInfo(e.player)
        PlayerModule.setFlag(e.player, WorldChangeEvent.worldNameFlag(e.player.world))
        Main.instance.adminModeCommand.hideHiddenFor(e.player)

        e.joinMessage = "§a+ §7${e.player.name}"
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {

        Main.instance.adminModeCommand.forceExit(e.player)

        //Last unregister player
        PlayerModule.remPlayer(e.player)
        StaticModule.updateTab()

        e.quitMessage = "§c- §7${e.player.name}"
    }

}