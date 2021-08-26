package nl.xanderwander.livesmp.modules

import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import nl.xanderwander.livesmp.utils.ChatFormat
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.Bukkit
import org.bukkit.Statistic
import org.bukkit.World
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.ceil


class SleepModule: BukkitRunnable() {

    private val bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID)

    override fun run() {

        val sleep = PlayerManager.count(PlayerFlag.IS_SLEEPING)
        val visiblePlayers = PlayerManager.reverseMatch(PlayerFlag.IS_HIDDEN)
        val inOverworld = PlayerManager.match(visiblePlayers, PlayerFlag.IN_OVERWORLD)
        val inWorld = ceil(inOverworld.size / 2.0).toInt()
        if (inWorld == 0) return
        val playerObject = inOverworld[0]

        if (!isNight(playerObject.world)) {
            if (playerObject.world.time <= 1L) {
                bossBar.color = BarColor.GREEN
                RunnableHelper.runLater(100L) {
                    bossBar.progress = 0.0
                    bossBar.color = BarColor.WHITE
                    bossBar.removeAll()
                }
                PlayerManager.setAllFlags(PlayerFlag.IS_SLEEPING, false)
            }
            return
        }

        val progress: Double = (sleep.toDouble() / inWorld).coerceAtMost(1.0)
        bossBar.progress = progress
        bossBar.setTitle("§4§lSlapen §8(§7$sleep§8/§7$inWorld§8)")

        if (progress == 1.0) {
            makeDay(inOverworld, playerObject.world)
        } else {
            PlayerManager.match(PlayerFlag.IN_OVERWORLD).forEach { bossBar.addPlayer(it) }
            PlayerManager.reverseMatch(PlayerFlag.IN_OVERWORLD).forEach { bossBar.removePlayer(it) }
            PlayerManager.all().forEach { if (it.isSleeping) { PlayerManager.setFlag(it, PlayerFlag.IS_SLEEPING) } }
        }

    }

    fun destroy() {
        bossBar.removeAll()
    }

    fun isFull(): Boolean {
        val sleep = PlayerManager.count(PlayerFlag.IS_SLEEPING)
        val players = arrayListOf<Player>()
        for (player in PlayerManager.reverseMatch(PlayerFlag.IS_HIDDEN)) {
            if (player.world.name != "world") continue
            players.add(player)
        }
        val inWorld = ceil(players.size / 2.0).toInt()
        if (inWorld == 0) return false
        val progress: Double = (sleep.toDouble() / inWorld).coerceAtMost(1.0)
        return (progress == 1.0)
    }

    private fun makeDay(players: ArrayList<Player>, world: World) {
        for (player in PlayerManager.match(PlayerFlag.IS_SLEEPING)) {
            player.setStatistic(Statistic.TIME_SINCE_REST,0)
        }
        world.time = 0L
        world.isThundering = false
        world.setStorm(false)
        ChatFormat.send(players, "Meer dan 50% wilde slapen, dus het is nu dag.")
    }

    fun isNight(world: World): Boolean {
        val time = world.time
        if (world.isThundering) return true
        if (world.hasStorm()  ) return (time in 12011..23991)
        return (time in 12542..23460)

    }

}