package nl.xanderwander.livesmp.chat

import nl.xanderwander.livesmp.Main
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent: Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        val player = event.player
        val prefix = Main.instance.luckPermsHook.playerPrefix(player)
        event.message = "$prefix §7$player §9» §f${event.message}"

    }

}