package nl.xanderwander.livesmp.chat

import net.luckperms.api.LuckPerms
import net.luckperms.api.model.user.User
import nl.xanderwander.livesmp.Main
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class LuckPermsHook {

    private lateinit var luckPerms: LuckPerms

    init {
        val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (provider != null) {
            luckPerms = provider.provider
        } else {
            Main.instance.logger.severe("LuckPerms could provider could not be loaded!")
        }
    }

    fun playerPrefix(p: Player): String {
        val user = luckPermsUser(p)
        val prefix = user?.cachedData?.metaData?.prefix ?: ""
        return prefix.replace("&", "§")
    }

    fun playerPrefixColor(p: Player): String {
        var prefix = playerPrefix(p).substring(0, 2)
        if (prefix.length < 2) prefix = "§7"
        return prefix
    }

    private fun luckPermsUser(p: Player): User? {
        return luckPerms.userManager.getUser(p.uniqueId)
    }

}