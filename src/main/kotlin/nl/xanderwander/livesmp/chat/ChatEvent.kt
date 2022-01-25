package nl.xanderwander.livesmp.chat

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.playerflags.PlayerFlag
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
        val prefix = Main.luckPermsHook.playerPrefix(player)
        val preMessage = "$prefix §7${player.name} §9» §f${StringUtils.removeIfFirst(event.message, "!")}"
        val message = ChatReplace().replace(player, preMessage)

        if (PlayerModule.getFlag(player, PlayerFlag.IS_HIDDEN) && !event.message.startsWith("!")) {
            for (receiver in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
                receiver.sendMessage("§8[Hidden] $message")
            }
            Main.instance.logger.info("§8[Hidden] $message")
            return
        } else {
            for (receiver in PlayerModule.all()) {
                receiver.sendMessage(message)
            }
        }
        Main.instance.logger.info(message)

    }

}