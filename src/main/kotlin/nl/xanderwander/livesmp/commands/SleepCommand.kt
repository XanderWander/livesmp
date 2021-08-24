package nl.xanderwander.livesmp.commands

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SleepCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender is Player) {

            if (PlayerModule.visiblePlayers.size <= 1 || !Main.instance.sleepModule.isNight(sender.world)) return true

            if (Main.instance.sleepModule.sleeping.contains(sender)) {
                Main.instance.sleepModule.sleeping.remove(sender)
                ChatFormat.sendCustom(sender, "Je gaat niet meer slapen.", "")
            } else {
                Main.instance.sleepModule.sleeping.add(sender)
                ChatFormat.sendCustom(sender, "Je gaat slapen.", "")

                val click = TextComponent(" §8» §a§lSlapen")
                click.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slapen")
                click.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier om te gaan slapen."))

                for (player in PlayerModule.allPlayers) {
                    if (player.world.name != "world") continue
                    ChatFormat.send(player, "§e${sender.name} §7wil gaan slapen.")
                    if (!Main.instance.sleepModule.isFull()) {
                        player.spigot().sendMessage(click)
                    }
                }
            }
        }

        return true

    }

}