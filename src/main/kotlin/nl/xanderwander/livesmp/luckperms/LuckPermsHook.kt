package nl.xanderwander.livesmp.luckperms

import nl.xanderwander.livesmp.Main
import org.bukkit.entity.Player

class LuckPermsHook {

    companion object {

        fun playerPrefix(p: Player): String {

            if (Main.instance.luckPerms == null) {
                return ""
            }

            return "@todo"

        }

    }

}