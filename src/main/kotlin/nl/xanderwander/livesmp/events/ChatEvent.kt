package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
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
        val prefix = Main.instance.luckPermsModule.playerPrefix(player)
        val message = "$prefix §7${player.name} §9» §f${StringUtils.removeIfFirst(event.message, "!")}"

        if (PlayerManager.getFlag(player, PlayerFlag.IS_HIDDEN) && !event.message.startsWith("!")) {
            for (receiver in PlayerManager.match(PlayerFlag.IS_HIDDEN)) {
                receiver.sendMessage("§8[Hidden] $message")
            }
            Main.instance.logger.info("§8[Hidden] $message")
            return
        } else {
            for (receiver in PlayerManager.all()) {
                receiver.sendMessage(message)
            }
        }
        Main.instance.logger.info(message)

    }

}