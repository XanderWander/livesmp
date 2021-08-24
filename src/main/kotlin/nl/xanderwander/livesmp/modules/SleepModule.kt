package nl.xanderwander.livesmp.modules

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.utils.ChatFormat
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.ceil


class SleepModule: BukkitRunnable() {

    val sleeping = arrayListOf<Player>()
    var isNight = false
    var bossBarRemoving = false
    private val bossBar = Bukkit.createBossBar("", BarColor.WHITE, BarStyle.SOLID)

    init {
        this.runTaskTimer(Main.instance, 0L, 1L)
    }

    override fun run() {

        if (PlayerModule.visiblePlayers.size <= 1) return

        val sleep = sleeping.size
        val players = arrayListOf<Player>()
        for (player in PlayerModule.visiblePlayers) {
            if (player.world.name != "world") continue
            players.add(player)
        }
        val inWorld = ceil(players.size / 2.0).toInt()
        if (inWorld == 0) return
        val playerObject = players[0]
        if (!isNight(playerObject.world)) {
            if (isNight) {
                bossBarRemoving = true
                RunnableHelper.runLater(100L) {
                    bossBarRemoving = false
                    bossBar.removeAll()
                }
            } else if (!bossBarRemoving) {
                bossBar.removeAll()
            }

            bossBar.color = BarColor.GREEN
            sleeping.clear()
            isNight = false
            return
        }
        isNight = true
        bossBar.color = BarColor.WHITE

        for (player in PlayerModule.allPlayers) {
            if (player.world.name != "world") continue
            if (!bossBar.players.contains(player)) bossBar.addPlayer(player)
            if (player.isSleeping && !sleeping.contains(player)) sleeping.add(player)

            val progress: Double = (sleep.toDouble() / inWorld).coerceAtMost(1.0)
            bossBar.progress = progress
            bossBar.setTitle("§4§lSlapen §8(§7$sleep§8/§7$inWorld§8)")

            if (progress >= 1.0) {
                makeDay(players, playerObject.world)
                return
            }
        }

    }

    fun destroy() {
        bossBar.removeAll()
    }

    fun isFull(): Boolean {
        val sleep = sleeping.size
        val players = arrayListOf<Player>()
        for (player in PlayerModule.visiblePlayers) {
            if (player.world.name != "world") continue
            players.add(player)
        }
        val inWorld = ceil(players.size / 2.0).toInt()
        if (inWorld == 0) return false
        val progress: Double = (sleep.toDouble() / inWorld).coerceAtMost(1.0)
        return (progress == 1.0)
    }

    fun makeDay(players: ArrayList<Player>,world: World) {
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