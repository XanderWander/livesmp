package nl.xanderwander.livesmp.menus.admin

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminMode: CommandExecutor {

    private val adminMode = hashMapOf<Player, Location>()

    fun forceExit(player: Player) {
        if (adminMode.contains(player)) {
            adminMode[player]?.let { player.teleport(it) }
            adminMode.remove(player)
            player.gameMode = GameMode.SURVIVAL
            Main.instance.playerModule.makeVisible(player)
            ChatFormat.sendCustom(player, "Admin mode is uit", "")
            for (visiblePlayer in PlayerModule.allPlayers) {
                visiblePlayer.showPlayer(Main.instance, player)
            }
            for (hiddenPlayer in PlayerModule.hiddenPlayers) {
                player.hidePlayer(Main.instance, hiddenPlayer)
            }
        }
    }

    private fun adminMode(player: Player, message: Boolean) {
        if (adminMode.contains(player)) {
            adminMode[player]?.let { player.teleport(it) }
            adminMode.remove(player)
            player.gameMode = GameMode.SURVIVAL
            Main.instance.playerModule.makeVisible(player)
            ChatFormat.sendCustom(player, "Admin mode is uit", "")
            for (visiblePlayer in PlayerModule.allPlayers) {
                visiblePlayer.showPlayer(Main.instance, player)
            }
            for (hiddenPlayer in PlayerModule.hiddenPlayers) {
                player.hidePlayer(Main.instance, hiddenPlayer)
            }
            if (message) {
                fakeMessage("§a+", player.name)
            }
        } else {
            adminMode[player] = player.location
            player.gameMode = GameMode.SPECTATOR
            Main.instance.playerModule.makeHidden(player)
            ChatFormat.sendCustom(player, "Admin mode is aan", "")
            for (visiblePlayer in PlayerModule.visiblePlayers) {
                visiblePlayer.hidePlayer(Main.instance, player)
            }
            for (hiddenPlayer in PlayerModule.hiddenPlayers) {
                player.showPlayer(Main.instance, hiddenPlayer)
            }
            if (message) {
                fakeMessage("§c-", player.name)
            }
        }
        Main.instance.tablist.updateTab()
    }

    private fun fakeMessage(msg: String, name: String) {
        for (visiblePlayer in PlayerModule.visiblePlayers) {
            visiblePlayer.sendMessage("$msg §7$name")
        }
        for (hiddenPlayer in PlayerModule.hiddenPlayers) {
            hiddenPlayer.sendMessage("$msg §8[Hidden] §7$name")
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("level.admin") && sender is Player) {

            if (args.isNotEmpty()) {
                if ("true|on|yes|y".contains(args[0])) {
                    adminMode(sender, true)
                    return true
                }
            }
            adminMode(sender, false)
        }
        return true
    }

}