package nl.xanderwander.livesmp.chat

import nl.xanderwander.livesmp.web.WebLoader
import org.bukkit.entity.Player

class ChatReplace {

    fun replace(player: Player, str: String): String {
        var text = str
        for (emoji in WebLoader.emojiList) {
            if (player.hasPermission(emoji.permission)) {
                text = text.replace(":${emoji.code}:", emoji.ascii)
            }
        }
        return text
    }

}