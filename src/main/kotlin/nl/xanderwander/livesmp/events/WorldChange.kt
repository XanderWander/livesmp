package nl.xanderwander.livesmp.events

import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import java.lang.IllegalArgumentException

class WorldChange: Listener {

    companion object {
        fun worldNameFlag(world: World): PlayerFlag {
            val name = world.name
                .replace("world_", "")
                .replace("the_", "")
                .replace("world", "overworld")
                .uppercase()
            return try { PlayerFlag.valueOf("IN_$name") }
            catch (e: IllegalArgumentException) { PlayerFlag.IN_OVERWORLD }
        }
    }

    @EventHandler
    fun onWorldChange(e: PlayerChangedWorldEvent) {
        PlayerManager.setFlag(e.player, worldNameFlag(e.from), false)
        PlayerManager.setFlag(e.player, worldNameFlag(e.player.world))
    }

}