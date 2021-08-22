package nl.xanderwander.livesmp.luckperms

import net.luckperms.api.model.user.User
import nl.xanderwander.livesmp.Main
import org.bukkit.entity.Player

class LuckPermsHook {

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
        return Main.instance.luckPerms?.userManager?.getUser(p.uniqueId)
    }

}