package nl.xanderwander.livesmp.player

import java.util.*

class PlayerFlags(
    private val flags: EnumMap<PlayerFlag, Boolean> = EnumMap(PlayerFlag::class.java)
) {

    init {
        PlayerFlag.values().forEach { flags[it] = false }
    }

    fun setFlag(flag: PlayerFlag, value: Boolean) {
        flags[flag] = value
    }

    fun getFlag(flag: PlayerFlag): Boolean {
        return flags[flag] ?: false
    }

}