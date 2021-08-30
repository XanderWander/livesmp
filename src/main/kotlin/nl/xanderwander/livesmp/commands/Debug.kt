package nl.xanderwander.livesmp.commands
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.player.PlayerFlag
import nl.xanderwander.livesmp.player.PlayerManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Debug: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val logger = Main.instance.logger
        val playersOnline = PlayerManager.all().size
        val playersSleeping = PlayerManager.count(PlayerFlag.IS_SLEEPING)
        val playersVisible = PlayerManager.reverseCount(PlayerFlag.IS_HIDDEN)
        val playersHidden = PlayerManager.count(PlayerFlag.IS_HIDDEN)

        logger.info("Total players online: $playersOnline")
        logger.info(" - Sleeping: $playersSleeping")
        logger.info(" - Visible: $playersVisible")
        logger.info(" - Hidden: $playersHidden")
        logger.info("")
        for (player in PlayerManager.all()) {
            logger.info("[${player.name}]")
            PlayerManager.debugShow(player)
            logger.info("")
        }

        return true
    }

}