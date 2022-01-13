package nl.xanderwander.livesmp.trails

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.reflection.ClassPrefix
import nl.xanderwander.livesmp.reflection.ReflectionUtil
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TrailManager: BukkitRunnable() {

    companion object {
        val instance = TrailManager()
    }

    init {
        this.runTaskTimerAsynchronously(Main.instance, 10L, 1L)
    }

    private val trails = hashMapOf<Player, TrailType>()

    fun registerTrail(player: Player, trail: TrailType) {
        trails[player] = trail
    }

    fun stopTrail(player: Player) {
        trails.remove(player)
    }

    private fun trailTick(player: Player, trail: TrailType) {
        val craftWorld = ReflectionUtil.invoke(player.world, "getHandle")
    }

    override fun run() {
        trails.forEach { (player, trail) -> trailTick(player, trail) }
    }

}