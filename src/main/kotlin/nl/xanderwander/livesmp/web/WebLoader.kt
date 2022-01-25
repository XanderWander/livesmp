package nl.xanderwander.livesmp.web

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import nl.xanderwander.livesmp.chat.Emoji
import nl.xanderwander.livesmp.utils.RunnableUtils
import org.bukkit.entity.Player
import java.net.URL

class WebLoader {

    companion object {
        private const val base_url = "http://resourcepacks.hyparks.com/"
        private const val emoji_url = "https://api.livesmp.com/emoji/?key=J72a3gHz0b"

        val emojiList = arrayListOf<Emoji>()

        fun setResourcePack(p: Player) {
            RunnableUtils.doAsync {
                val text = URL(base_url + "latest.php").readText()
                val json = Gson().fromJson(text, JsonObject::class.java)
                val zip = json.get("file").asString
                p.setResourcePack(base_url + zip)
            }
        }

        fun resetResourcePack(p: Player) {
            RunnableUtils.doAsync {
                p.setResourcePack("https://reset.com/")
            }
        }

        fun loadEmojiList() {
            RunnableUtils.doAsync {
                val text = URL(emoji_url).readText()
                val json = Gson().fromJson(text, JsonArray::class.java)
                for (e in json) {
                    val emoji = e.asJsonObject
                    emojiList.add(
                        Emoji(
                            code = emoji.get("code").asString,
                            ascii = emoji.get("ascii").asString,
                            permission = emoji.get("permission").asString
                        )
                    )
                }
            }
        }

    }

}