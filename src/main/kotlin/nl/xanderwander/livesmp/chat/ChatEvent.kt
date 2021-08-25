package nl.xanderwander.livesmp.chat

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.StringUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent: Listener {

    @EventHandler(priority = EventPriority.LOW)
    fun onChat(event: AsyncPlayerChatEvent) {

        event.isCancelled = true

        val player = event.player
        val prefix = Main.instance.luckPermsHook.playerPrefix(player)
        val playerModule = Main.instance.playerModule
        val message = "$prefix §7${player.name} §9» §f${StringUtils.removeIfFirst(event.message, "!")}"

        if (playerModule.isHidden(player) && !event.message.startsWith("!")) {
            for (receiver in PlayerModule.hiddenPlayers) {
                receiver.sendMessage("§8[Hidden] $message")
            }
            Main.instance.logger.info("§8[Hidden] $message")
            return
        } else {
            for (receiver in PlayerModule.allPlayers) {
                receiver.sendMessage(message)
            }
        }
        Main.instance.logger.info(message)

    }

}