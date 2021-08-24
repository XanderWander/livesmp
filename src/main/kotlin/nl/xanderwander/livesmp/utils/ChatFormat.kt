package nl.xanderwander.livesmp.utils

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.entity.Player

class ChatFormat {

    companion object {

        val red: ChatColor = ChatColor.of("#ff0000")
        val prefix = "$red§lLive§f§lSMP"
        val cpf = "$prefix §8»§7"

        fun send(player: Player, message: String) {
            player.sendMessage("$cpf $message")
        }

        fun send(players: ArrayList<Player>, message: String) {
            for (player in players) {
                send(player, message)
            }
        }

        fun sendCustom(player: Player, message: String, prefix: String) {
            player.sendMessage("$red$prefix §8» §7$message")
        }
    }

}