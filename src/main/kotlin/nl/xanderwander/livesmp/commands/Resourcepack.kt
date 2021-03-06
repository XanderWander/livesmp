package nl.xanderwander.livesmp.commands

import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.web.WebLoader
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Resourcepack: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name == "rrp") {
                WebLoader.resetResourcePack(sender)
            } else {
                WebLoader.setResourcePack(sender)
            }
        }
        return true
    }

}