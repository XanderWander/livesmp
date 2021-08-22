package nl.xanderwander.livesmp.utils

import nl.xanderwander.livesmp.Main
import org.bukkit.scheduler.BukkitRunnable

class RunnableHelper {

    companion object {

        private fun runLater(wait: Long, f: () -> Any) {
            object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }.runTaskLater(Main.instance, wait)
        }

    }

}