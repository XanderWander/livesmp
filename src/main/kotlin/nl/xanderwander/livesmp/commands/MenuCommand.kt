package nl.xanderwander.livesmp.commands

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MenuCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (label == "admin") {
            if (sender is Player) {
                Main.instance.menuInstances.adminMenu.openTo(sender)
            }
            return true
        }

        if (args[0] == "streamer") {
            if (sender is Player) {
                Main.instance.menuInstances.streamerMenu.openTo(sender)
            }
        }

        if (args[0] == "bc") {
            Bukkit.broadcastMessage("Fakka $args")
        }

        if (args[0] == "perm") {
            sender.sendMessage("${(sender.hasPermission("tag.noob"))}")
        }

        val player = sender as Player

        if (args[0] == "test") {

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
            website.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor onze discord"))
            discord.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor website"))
            streamer.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier voor de aangesloten streamers"))

            help.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/help.php")
            regels.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/regels.php")
            website.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/")
            discord.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.com/invite/2shhevVeXx")
            streamer.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://livesmp.com/streamers.php")

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

        return true
    }

}