package nl.xanderwander.livesmp.utils

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender

val red: ChatColor = ChatColor.of("#ff0000")
val prefix = "$red§lLive§f§lSMP"
val chatPrefix = "$prefix §8»§7"

fun CommandSender.send(message: String, prefix: String = chatPrefix) {
    if (prefix != chatPrefix) {
        this.sendMessage("$red$prefix $message")
    } else {
        this.sendMessage("$prefix $message")
    }
}