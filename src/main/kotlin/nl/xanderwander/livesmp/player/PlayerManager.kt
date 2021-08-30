package nl.xanderwander.livesmp.player

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.events.WorldChange
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PlayerManager {

    companion object {
        private val players = hashMapOf<Player, PlayerFlags>()

        fun addPlayer(player: Player) {
            if (!players.containsKey(player)) {
                players[player] = PlayerFlags()
            }
        }

        fun remPlayer(player: Player) {
            players.remove(player)
        }

        fun getPlayer(player: Player): PlayerFlags {
            if (!players.containsKey(player)) addPlayer(player)
            return players[player]!!
        }

        fun setFlag(player: Player, flag: PlayerFlag, value: Boolean = true) {
            players[player]?.setFlag(flag, value)
        }

        fun getFlag(player: Player, flag: PlayerFlag): Boolean {
            return players[player]?.getFlag(flag) ?: false
        }

        fun setAllFlags(flag: PlayerFlag, value: Boolean) {
            players.keys.forEach{ players[it]!!.setFlag(flag, value) }
        }

        fun all(): MutableSet<Player> {
            return players.keys
        }

        fun count(vararg flags: PlayerFlag): Int {
            return match(*flags).size
        }

        fun reverseCount(vararg flags: PlayerFlag): Int {
            return reverseMatch(*flags).size
        }

        fun match(vararg flags: PlayerFlag): ArrayList<Player> {
            val matched = arrayListOf<Player>()
            for (player in players.keys) {
                if (match(player, *flags)) {
                    matched.add(player)
                }
            }
            return matched
        }

        fun match(players: ArrayList<Player>, vararg flags: PlayerFlag): ArrayList<Player> {
            val matched = arrayListOf<Player>()
            for (player in players) {
                if (match(player, *flags)) {
                    matched.add(player)
                }
            }
            return matched
        }

        private fun match(player: Player, vararg flags: PlayerFlag): Boolean {
            for (flag in flags) {
                if (players[player]?.getFlag(flag) == false) {
                    return false
                }
            }
            return true
        }

        fun reverseMatch(vararg flags: PlayerFlag): ArrayList<Player> {
            val matched = arrayListOf<Player>()
            for (player in players.keys) {
                if (reverseMatch(player, *flags)) {
                    matched.add(player)
                }
            }
            return matched
        }

        private fun reverseMatch(player: Player, vararg flags: PlayerFlag): Boolean {
            for (flag in flags) {
                if (players[player]?.getFlag(flag) == true) {
                    return false
                }
            }
            return true
        }

        fun initialize() {
            Bukkit.getOnlinePlayers().forEach{
                addPlayer(it)
                setFlag(it, WorldChange.worldNameFlag(it.world))
            }
        }

        fun destroy() {
            players.clear()
        }

        fun debugShow(player: Player) {
            val logger = Main.instance.logger

            logger.info("-----------------------------------------------")
            logger.info("Debug flags of: ${player.name}")
            logger.info("")
            for (flag in PlayerFlag.values()) {
                logger.info(" - ${flag.name} = ${getFlag(player, flag)}")
            }
            logger.info("")
            logger.info("-----------------------------------------------")
        }

    }

}