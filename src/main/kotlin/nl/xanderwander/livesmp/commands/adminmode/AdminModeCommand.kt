package nl.xanderwander.livesmp.commands.adminmode

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.playerflags.PlayerFlag
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.send
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminModeCommand: CommandExecutor {

    private val adminMode = hashMapOf<Player, Location>()
    private val bossBar = Bukkit.createBossBar("§4AdminMode", BarColor.RED, BarStyle.SOLID)

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

    fun forceExit(player: Player, pluginDisable: Boolean = false) {
        if (adminMode.contains(player)) {
            resetAdminMode(player, pluginDisable)
        }
    }

    fun hideHiddenFor(player: Player) {
        for (hiddenPlayer in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
            player.hidePlayer(Main.instance, hiddenPlayer)
        }
    }

    private fun adminMode(player: Player, message: Boolean) {
        if (adminMode.contains(player)) {
            resetAdminMode(player)
            if (message) {
                fakeMessage("§a+", player.name)
            }
        } else {
            setAdminMode(player)
            if (message) {
                fakeMessage("§c-", player.name)
            }
        }
        StaticModule.updateTab()
    }

    private fun fakeMessage(msg: String, name: String) {
        for (visiblePlayer in PlayerModule.reverseMatch(PlayerFlag.IS_HIDDEN)) {
            visiblePlayer.sendMessage("$msg §7$name")
        }
        for (hiddenPlayer in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
            hiddenPlayer.sendMessage("$msg §8[Hidden] §7$name")
        }
    }

    private fun setAdminMode(player: Player) {
        bossBar.addPlayer(player)
        adminMode[player] = player.location
        player.gameMode = GameMode.SPECTATOR
        PlayerModule.getPlayer(player).setFlag(PlayerFlag.IS_HIDDEN, true)
        player.send("Admin mode is aan")
        for (visiblePlayer in PlayerModule.reverseMatch(PlayerFlag.IS_HIDDEN)) {
            visiblePlayer.hidePlayer(Main.instance, player)
        }
        for (hiddenPlayer in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
            player.showPlayer(Main.instance, hiddenPlayer)
        }
    }

    private fun resetAdminMode(player: Player, pluginDisable: Boolean = false) {
        bossBar.removePlayer(player)
        player.teleport(adminMode[player]!!)
        adminMode.remove(player)
        player.gameMode = GameMode.SURVIVAL
        PlayerModule.getPlayer(player).setFlag(PlayerFlag.IS_HIDDEN, false)
        player.send("Admin mode is uit")
        for (visiblePlayer in PlayerModule.all()) {
            visiblePlayer.showPlayer(Main.instance, player)
        }
        if (pluginDisable) return
        for (hiddenPlayer in PlayerModule.match(PlayerFlag.IS_HIDDEN)) {
            player.hidePlayer(Main.instance, hiddenPlayer)
        }
    }

}