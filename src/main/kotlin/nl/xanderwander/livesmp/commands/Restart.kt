package nl.xanderwander.livesmp.commands

import nl.xanderwander.livesmp.Main
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable

class Restart: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.hasPermission("server.restart")) {

            var index = 0
            object: BukkitRunnable() {
                override fun run() {
                    Bukkit.getOnlinePlayers().forEach {
                        it.sendTitle("§6Let op de server gaat restarten!", "§7${30 - index}..", 0, 20, 10)
                    }
                    index++
                    if (index == 30) {
                        Bukkit.getOnlinePlayers().forEach {
                            it.kickPlayer("§cDe server is aan het restarten.")
                        }
                    }
                    if (index == 31) {
                        Bukkit.getServer().spigot().restart()
                        this.cancel()
                    }
                }
            }.runTaskTimer(Main.instance, 0L, 20L)

        }

        return true

    }

}