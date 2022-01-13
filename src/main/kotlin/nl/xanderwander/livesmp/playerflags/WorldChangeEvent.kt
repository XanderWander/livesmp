package nl.xanderwander.livesmp.playerflags

import nl.xanderwander.livesmp.modules.PlayerModule
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import java.lang.IllegalArgumentException

class WorldChangeEvent: Listener {

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
        PlayerModule.setFlag(e.player, worldNameFlag(e.from), false)
        PlayerModule.setFlag(e.player, worldNameFlag(e.player.world))
    }

}