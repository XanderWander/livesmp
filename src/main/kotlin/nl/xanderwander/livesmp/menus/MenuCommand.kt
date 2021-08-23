package nl.xanderwander.livesmp.menus

import nl.xanderwander.livesmp.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MenuCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args[0] == "streamer") {
            if (sender is Player) {
                Main.instance.menuInstances.streamerMenu.openTo(sender)
            }
        }
        return true
    }

}