package nl.xanderwander.livesmp.trails

import nl.xanderwander.livesmp.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TrailCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            when (args[0]) {
                "start" -> {
                    if (args.size >= 2) {
                        val trailType = try {
                            TrailType.valueOf(args[1].uppercase())
                        } catch (e: IllegalArgumentException) {
                            TrailType.COPPER
                        }
                        Main.trailManager.registerTrail(sender, trailType)
                    } else {
                        Main.trailManager.registerTrail(sender, TrailType.COPPER)
                    }
                }
                "stop" -> Main.trailManager.stopTrail(sender)
            }
        }
        return true
    }

}