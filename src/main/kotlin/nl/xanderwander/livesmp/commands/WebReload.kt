package nl.xanderwander.livesmp.commands

import nl.xanderwander.livesmp.web.WebLoader
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WebReload: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.hasPermission("level.admin")) {
            WebLoader.loadEmojiList()
        }

        return true

    }
}