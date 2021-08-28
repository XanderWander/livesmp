package nl.xanderwander.livesmp.commands

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import nl.xanderwander.livesmp.utils.ChatFormat
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AdminMode: CommandExecutor {

    private val adminMode = hashMapOf<Player, Location>()
    private val bossBar = Bukkit.createBossBar("§8AdminMode", BarColor.BLUE, BarStyle.SOLID)

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

    fun forceExit(player: Player) {
        if (adminMode.contains(player)) {
            resetAdminMode(player)
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
        for (visiblePlayer in PlayerManager.reverseMatch(PlayerFlag.IS_HIDDEN)) {
            visiblePlayer.sendMessage("$msg §7$name")
        }
        for (hiddenPlayer in PlayerManager.match(PlayerFlag.IS_HIDDEN)) {
            hiddenPlayer.sendMessage("$msg §8[Hidden] §7$name")
        }
    }

    private fun setAdminMode(player: Player) {
        bossBar.addPlayer(player)
        adminMode[player] = player.location
        player.gameMode = GameMode.SPECTATOR
        PlayerManager.getPlayer(player).setFlag(PlayerFlag.IS_HIDDEN, true)
        ChatFormat.sendCustom(player, "Admin mode is aan", "")
        for (visiblePlayer in PlayerManager.reverseMatch(PlayerFlag.IS_HIDDEN)) {
            visiblePlayer.hidePlayer(Main.instance, player)
        }
        for (hiddenPlayer in PlayerManager.match(PlayerFlag.IS_HIDDEN)) {
            player.showPlayer(Main.instance, hiddenPlayer)
        }
    }

    private fun resetAdminMode(player: Player) {
        bossBar.removePlayer(player)
        player.teleport(adminMode[player]!!)
        adminMode.remove(player)
        player.gameMode = GameMode.SURVIVAL
        PlayerManager.getPlayer(player).setFlag(PlayerFlag.IS_HIDDEN, false)
        ChatFormat.sendCustom(player, "Admin mode is uit", "")
        for (visiblePlayer in PlayerManager.all()) {
            visiblePlayer.showPlayer(Main.instance, player)
        }
        for (hiddenPlayer in PlayerManager.match(PlayerFlag.IS_HIDDEN)) {
            player.hidePlayer(Main.instance, hiddenPlayer)
        }
    }

}