package nl.xanderwander.livesmp.commands

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Spawn: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender is Player) {
            if (sender.world.name == "world") {
                sender.teleport(Location(sender.world, 0.5, 71.0, 0.5))
            }
        }

        return true

    }
}