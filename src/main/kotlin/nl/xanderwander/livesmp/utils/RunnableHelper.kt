package nl.xanderwander.livesmp.utils

import nl.xanderwander.livesmp.Main
import org.bukkit.scheduler.BukkitRunnable

class RunnableHelper {

    companion object {

        fun runLater(wait: Long, f: () -> Any) {
            object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }.runTaskLater(Main.instance, wait)
        }

        fun doSync(f: () -> Any) {
            object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }.runTask(Main.instance)
        }

        fun doAsync(f: () -> Any) {
            object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }.runTaskAsynchronously(Main.instance)
        }

        fun run(wait: Long, period: Long, f: () -> Any): BukkitRunnable {
            val runnable = object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }
            runnable.runTaskTimer(Main.instance, wait, period)
            return runnable
        }

        fun run(period: Long, f: () -> Any): BukkitRunnable {
            return run(0L, period) { f() }
        }

        fun runAsync(wait: Long, period: Long, f: () -> Any): BukkitRunnable {
            val runnable = object : BukkitRunnable() {
                override fun run() {
                    f.invoke()
                }
            }
            runnable.runTaskTimerAsynchronously(Main.instance, wait, period)
            return runnable
        }

        fun runAsync(period: Long, f: () -> Any): BukkitRunnable {
            return runAsync(0L, period) { f() }
        }

        fun runIndexed(wait: Long, period: Long, f: (Int) -> Unit): BukkitRunnable {
            var count = 0
            val runnable = object : BukkitRunnable() {
                override fun run() {
                    f.invoke(count)
                    count++
                }
            }
            runnable.runTaskTimer(Main.instance, wait, period)
            return runnable
        }

    }

}