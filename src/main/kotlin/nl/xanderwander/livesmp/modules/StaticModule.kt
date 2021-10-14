package nl.xanderwander.livesmp.modules

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class StaticModule {

    companion object {
        private const val url = "http://resourcepacks.hyparks.com/latest.php"

        fun setResourcePack(p: Player) {
            p.setResourcePack(url)
        }

        fun resetResourcePack(p: Player) {
            p.setResourcePack("http://reset.com/")
        }

        fun updateTab() {
            for (p: Player in PlayerManager.all()) {
                val t = PlayerManager.reverseCount(PlayerFlag.IS_HIDDEN)
                val red = ChatColor.of("#ff0000")
                val mul = if (t != 1) listOf("zijn", "spelers") else listOf("is", "speler")
                p.setPlayerListHeaderFooter(
                    "\n" +
                            "${ChatFormat.prefix}\n" +
                            "",
                    "\n" +
                            "§7 Er ${mul[0]} op dit moment ${red}$t §7${mul[1]} online. \n" +
                            "\n" +
                            "${red}§nLiveSMP.com\n" +
                            ""
                )
            }
        }

        fun joinInfo(player: Player) {
            val message = TextComponent("     ")
            val help = TextComponent("§e[Help]")
            val spaces1 = TextComponent("      ")
            val regels = TextComponent("§e[Regels]")
            val spaces2 = TextComponent("      ")
            val website = TextComponent("§e[Website]")

            val message2 = TextComponent("         ")
            val discord = TextComponent("§e[Discord]")
            val spaces3 = TextComponent("       ")
            val streamer = TextComponent("§e[Streamers]")

            help.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor hulp"))
            regels.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor onze regels"))
            website.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor onze website"))
            discord.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor onze discord"))
            streamer.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor de aangesloten streamers"))

            help.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/help")
            regels.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/regels")
            website.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/")
            discord.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/2shhevVeXx")
            streamer.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/streamers")

            message.addExtra(help)
            message.addExtra(spaces1)
            message.addExtra(regels)
            message.addExtra(spaces2)
            message.addExtra(website)
            message.addExtra(spaces3)

            message2.addExtra(discord)
            message2.addExtra(spaces2)
            message2.addExtra(streamer)

            player.sendMessage("§8§l§m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯")
            player.sendMessage("")
            player.sendMessage("      §7Welkom op de ${ChatFormat.prefix}§r §7server")
            player.sendMessage("")
            player.spigot().sendMessage(ChatMessageType.CHAT, message)
            player.sendMessage("")
            player.spigot().sendMessage(ChatMessageType.CHAT, message2)
            player.sendMessage("")
            player.sendMessage("§8§l§m⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯")
        }
    }

}