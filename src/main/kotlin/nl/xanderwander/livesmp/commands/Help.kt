package nl.xanderwander.livesmp.commands

import nl.xanderwander.livesmp.modules.StaticModule
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Help: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            StaticModule.joinInfo(sender)
        }
        return true
    }
}