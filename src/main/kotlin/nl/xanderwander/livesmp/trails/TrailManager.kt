package nl.xanderwander.livesmp.trails

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.reflection.ClassPrefix
import nl.xanderwander.livesmp.reflection.ReflectionUtil
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class TrailManager: BukkitRunnable() {

    init {
        this.runTaskTimer(Main.instance, 10L, 5L)
    }

    private val trails = hashMapOf<Player, TrailItem>()
    val removeIDS = arrayListOf<Int>()

    fun registerTrail(player: Player, trail: TrailType) {
        when(trail) {
            TrailType.COPPER -> trails[player] = TrailItem(ItemStack(Material.COPPER_INGOT))
            TrailType.IRON -> trails[player] = TrailItem(ItemStack(Material.IRON_INGOT))
            TrailType.GOLD -> trails[player] = TrailItem(ItemStack(Material.GOLD_INGOT))
            TrailType.DIAMOND -> trails[player] = TrailItem(ItemStack(Material.DIAMOND))
            TrailType.NETHERITE -> trails[player] = TrailItem(ItemStack(Material.NETHERITE_INGOT))
        }
    }

    fun stopTrail(player: Player) {
        trails.remove(player)
    }

    private fun trailTick(player: Player, trail: TrailItem) {
        val xOff = cos((player.location.yaw - 90) / 180 * PI)
        val zOff = sin((player.location.yaw - 90) / 180 * PI)
        trail.spawn(player.location.add(xOff, 0.5, zOff))
    }

    override fun run() {
        trails.forEach { (player, trail) ->
            if (player.isOnline) {
                trailTick(player, trail)
            } else {
                stopTrail(player)
            }
        }
    }

}