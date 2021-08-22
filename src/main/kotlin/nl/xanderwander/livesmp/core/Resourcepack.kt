package nl.xanderwander.livesmp.core

import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

open class Resourcepack: Listener, CommandExecutor {

    @EventHandler
    open fun onJoin(event: PlayerJoinEvent) {
        val p = event.player

        RunnableHelper.runLater(5 * 20L) {
            p.setResourcePack("http://resourcepacks.hyparks.com/latest.php")
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {


        return true
    }

}