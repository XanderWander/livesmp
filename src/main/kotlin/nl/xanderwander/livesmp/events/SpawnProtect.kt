package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.utils.send
import org.bukkit.*
import org.bukkit.entity.Firework
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.spigotmc.event.player.PlayerSpawnLocationEvent

class SpawnProtect: Listener {

    @EventHandler
    fun onEntityExplode(event: EntityExplodeEvent) {
        if (spawnProtect(event.entity.location)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) {
        if (spawnProtect(event.block.location)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockExplode(event: EntitySpawnEvent) {
        if (event.entity is Item) return
        if (event.entity is Firework) return
        if (spawnProtect(event.entity.location)) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerSpawn(event: PlayerSpawnLocationEvent) {
        if (event.player.world.name != "world") return
        if (spawnProtect(event.player.location)) {
            event.spawnLocation = Location(event.player.world, 0.5, 71.0, 0.5)
        }
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.entity is Player) {
            if (event.damager is Player) {
                event.damage = 0.0
            }
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            if (spawnProtect(event.block.location)) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            if (spawnProtect(event.block.location)) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onBucketPlace(event: PlayerBucketEmptyEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            if (spawnProtect(event.block.location)) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onBucketFill(event: PlayerBucketFillEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            if (spawnProtect(event.block.location)) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onInteractBlock(event: PlayerInteractEvent) {
        if (event.player.gameMode != GameMode.CREATIVE) {
            if (event.hasBlock() && event.clickedBlock!!.type.isInteractable) {
                if (spawnProtect(event.clickedBlock!!.location)) {
                    event.isCancelled = true
                }
            }
        }
//        if (event.player.name == "XanderWander") {
//            event.player.send("${event.clickedBlock} \n${event.action}\n ${event.hand}\n ${event.hasBlock()}\n ${event.hasItem()}\n ${event.isBlockInHand}")
//        }
    }

    private fun spawnProtect(loc: Location): Boolean {

        if (loc.world?.name == "world_the_end") return false
        val size = if (loc.world?.name == "world_nether") 10.0 else 75.0
        if (loc.x >= size) return false
        if (loc.x < -size) return false
        if (loc.z >= size) return false
        if (loc.z < -size) return false
        return true

    }

}