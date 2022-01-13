package nl.xanderwander.livesmp.commands.sleep

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.playerflags.PlayerFlag
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.send
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SleepCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender is Player) {

            if (PlayerModule.reverseCount(PlayerFlag.IS_HIDDEN) <= 1 || !Main.instance.sleepModule.isNight(sender.world)) return true

            if (PlayerModule.getFlag(sender, PlayerFlag.IS_SLEEPING)) {
                PlayerModule.setFlag(sender, PlayerFlag.IS_SLEEPING, false)
                sender.send("Je gaat niet meer slapen.")
            } else {
                PlayerModule.setFlag(sender, PlayerFlag.IS_SLEEPING)
                sender.send("Je gaat slapen.")

                val click = TextComponent(" §8» §a§lSlapen")
                click.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slapen")
                click.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("§7Klik hier om te gaan slapen."))

                for (player in PlayerModule.all()) {
                    if (player.world.name != "world") continue
                    player.send("§e${sender.name} §7wil gaan slapen.")
                    if (!Main.instance.sleepModule.isFull()) {
                        player.spigot().sendMessage(click)
                    }
                }
            }
        }

        return true

    }

}