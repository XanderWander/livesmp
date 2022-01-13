package nl.xanderwander.livesmp.commands.dev
import net.md_5.bungee.api.ChatColor
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.playerflags.PlayerFlag
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.RunnableUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.SoundCategory
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.scheduler.BukkitRunnable

const val spawn_protect_size = 20.0

class Debug: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (!sender.hasPermission("op.op")) return false

        if (args.isNotEmpty() && args[0] == "openserver") {
            openServer()
        }

        if (args.isNotEmpty() && args[0] == "resetserver") {
            resetServer()
        }

        val logger = Main.instance.logger
        val playersOnline = PlayerModule.all().size
        val playersSleeping = PlayerModule.count(PlayerFlag.IS_SLEEPING)
        val playersVisible = PlayerModule.reverseCount(PlayerFlag.IS_HIDDEN)
        val playersHidden = PlayerModule.count(PlayerFlag.IS_HIDDEN)

        logger.info("Total players online: $playersOnline")
        logger.info(" - Sleeping: $playersSleeping")
        logger.info(" - Visible: $playersVisible")
        logger.info(" - Hidden: $playersHidden")
        logger.info("")
        for (player in PlayerModule.all()) {
            logger.info("[${player.name}]")
            PlayerModule.debugShow(player)
            logger.info("")
        }

        return true
    }

    private fun openServer() {
        var runnable: BukkitRunnable? = null
        runnable = RunnableUtils.runIndexed(0L, 1L) { index ->

            val time = index / 20

            if (index % 20 == 0) {

                when(time) {
                    0 -> {
                        moveBorders(150.0, 50)
                        PlayerModule.all().forEach {
                            val loc = Location(it.world, 0.5, 71.0, 0.5)
                            it.playSound(loc, "event.raid.horn", SoundCategory.MASTER, 100000F, 1F)
                            it.playSound(loc, "music_disc.otherside", SoundCategory.MASTER, 6.5F, 1F)
                        }
                    }
                    50 -> {
                        moveBorders(150.0, 0);
                        moveBorders(149.9, Int.MAX_VALUE);
                        PlayerModule.all().forEach { it.playSound(it.location, "block.end_portal.spawn", SoundCategory.MASTER, 50F, 0F) }
                    }
                    60 -> moveBorders(200000.0, 100000)
                    61 -> runnable?.cancel()
                }

                if (time < 50) {
                    val t = 60 - time
                    for (player in PlayerModule.all()) {
                        player.sendTitle("${ChatColor.DARK_GREEN}Server opening", "${ChatColor.GREEN}$t", 0, 20, 10)
                    }
                } else if (time < 60) {
                    val t = 60 - time
                    for (player in PlayerModule.all()) {
                        player.sendTitle("${ChatColor.DARK_RED}Server opening", "${ChatColor.RED}$t", 0, 20, 10)
                    }
                } else if (time == 60) {
                    PlayerModule.all().forEach { it.playSound(it.location, "ui.toast.challenge_complete", SoundCategory.MASTER, 50F, 0.7F) }
                    for (player in PlayerModule.all()) {
                        player.sendTitle("${ChatColor.DARK_AQUA}Server opening", "${ChatColor.AQUA}GOOO!!!!", 0, 20, 10)
                    }
                }

            }

        }
    }

    private fun resetServer() {
        setBorder(Bukkit.getWorld("world") ?: return)
        setBorder(Bukkit.getWorld("world_nether") ?: return)
        setBorder(Bukkit.getWorld("world_the_end") ?: return)
    }

    private fun moveBorders(size: Double, time: Int) {
        moveBorder(Bukkit.getWorld("world") ?: return, size, time)
        moveBorder(Bukkit.getWorld("world_nether") ?: return, size / 8, time)
        moveBorder(Bukkit.getWorld("world_the_end") ?: return, size, time)
    }

    private fun moveBorder(world: World, size: Double, time: Int) {
        world.worldBorder.setSize(size, time.toLong())
    }

    private fun setBorder(world: World) {
        world.worldBorder.size = spawn_protect_size
    }



}