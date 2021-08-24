package nl.xanderwander.livesmp.modules

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerModule: Listener {

    companion object {
        val allPlayers = arrayListOf<Player>()
        val visiblePlayers = arrayListOf<Player>()
        val hiddenPlayers = arrayListOf<Player>()

        private val joinListeners = arrayListOf<(event: PlayerJoinEvent) -> Unit>()
        private val quitListeners = arrayListOf<(event: PlayerQuitEvent) -> Unit>()

        fun onJoin(f: (event: PlayerJoinEvent) -> Unit) {
            joinListeners.add(f)
        }

        fun onQuit(f: (event: PlayerQuitEvent) -> Unit) {
            quitListeners.add(f)
        }
    }

    fun register(player: Player) {
        if (!allPlayers.contains(player)) {
            allPlayers.add(player)
            visiblePlayers.add(player)
        }
    }

    fun remove(player: Player) {
        allPlayers.remove(player)
        visiblePlayers.remove(player)
        hiddenPlayers.remove(player)
    }

    fun makeVisible(player: Player) {
        if (hiddenPlayers.contains(player)) {
            hiddenPlayers.remove(player)
            visiblePlayers.add(player)
        }
    }

    fun makeHidden(player: Player) {
        if (visiblePlayers.contains(player)) {
            visiblePlayers.remove(player)
            hiddenPlayers.add(player)
        }
    }

    fun isVisible(player: Player): Boolean {
        return visiblePlayers.contains(player)
    }

    fun isHidden(player: Player): Boolean {
        return hiddenPlayers.contains(player)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        register(event.player)

        for (f in joinListeners) {
            f.invoke(event)
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        remove(event.player)

        for (f in quitListeners) {
            f.invoke(event)
        }
    }

}