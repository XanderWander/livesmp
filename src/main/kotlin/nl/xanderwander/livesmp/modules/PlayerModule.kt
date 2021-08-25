package nl.xanderwander.livesmp.modules

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerModule: Listener {

    companion object {
        val allPlayers = arrayListOf<Player>()
        val visiblePlayers = arrayListOf<Player>()
        val hiddenPlayers = arrayListOf<Player>()
    }

    fun register(player: Player) {
        if (!allPlayers.contains(player)) {
            allPlayers.add(player)
            visiblePlayers.add(player)
        }
    }

    fun remove(player: Player) {
        allPlayers.remove(player)
        visiblePlayers.remove(player)
        hiddenPlayers.remove(player)
    }

    fun makeVisible(player: Player) {
        if (hiddenPlayers.contains(player)) {
            hiddenPlayers.remove(player)
            visiblePlayers.add(player)
        }
    }

    fun makeHidden(player: Player) {
        if (visiblePlayers.contains(player)) {
            visiblePlayers.remove(player)
            hiddenPlayers.add(player)
        }
    }

    fun isVisible(player: Player): Boolean {
        return visiblePlayers.contains(player)
    }

    fun isHidden(player: Player): Boolean {
        return hiddenPlayers.contains(player)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        register(event.player)

        event.joinMessage = "§a+ §7${event.player.name}"

        for (player in hiddenPlayers) {
            event.player.hidePlayer(Main.instance, player)
        }

        val player = event.player

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

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        remove(event.player)

        event.quitMessage = "§c- §7${event.player.name}"
        Main.instance.adminMode.forceExit(event.player)

        Main.instance.sleepModule.sleeping.remove(event.player)
    }

}