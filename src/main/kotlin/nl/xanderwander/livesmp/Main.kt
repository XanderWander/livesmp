package nl.xanderwander.livesmp

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import nl.xanderwander.livesmp.chat.LuckPermsHook
import nl.xanderwander.livesmp.commands.adminmode.AdminModeCommand
import nl.xanderwander.livesmp.itemprotect.InventoryStorage
import nl.xanderwander.livesmp.modules.RegisterModule
import nl.xanderwander.livesmp.modules.SleepModule
import nl.xanderwander.livesmp.modules.StaticModule
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.reflection.packets.PacketEntityDestroy
import nl.xanderwander.livesmp.trails.TrailManager
import nl.xanderwander.livesmp.web.WebLoader
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
        lateinit var manager: ProtocolManager
        lateinit var luckPermsHook: LuckPermsHook
        lateinit var version: String
        lateinit var trailManager: TrailManager
        val inventoryStorage = InventoryStorage()
    }

    private val registerModule = RegisterModule()

    val sleepModule = SleepModule()
    val adminModeCommand = AdminModeCommand()

    override fun onEnable() {

        instance = this
        manager = ProtocolLibrary.getProtocolManager()
        version = server.javaClass.getPackage().name.split(".")[3]
        luckPermsHook = LuckPermsHook()
        trailManager = TrailManager()

        registerModule.register(this)
        sleepModule.runTaskTimer(this, 0L, 1L)

        WebLoader.loadEmojiList()
        PlayerModule.initialize()
        StaticModule.updateTab()

        logger.info("${description.name} V${description.version} has been enabled.")

    }

    override fun onDisable() {

        for (player in PlayerModule.all()) {
            adminModeCommand.forceExit(player, true)
        }
        for (id in trailManager.removeIDS) {
            PacketEntityDestroy(id).send(PlayerModule.all())
        }

        sleepModule.cancel()
        sleepModule.destroy()
        PlayerModule.destroy()
        trailManager.cancel()

        logger.info("${description.name} has been disabled.")
    }

}

//manager.addPacketListener(object : PacketAdapter(Main.instance, ListenerPriority.NORMAL, PacketType.Play.Server.SPAWN_ENTITY) {
//    override fun onPacketSending(event: PacketEvent?) {
//        //Bukkit.broadcastMessage("${event?.packet?.integers?.values}")
//        val handle = event?.packet ?: return
////                Bukkit.broadcastMessage("Entity ID: ${handle.integers.read(0)}")
////                Bukkit.broadcastMessage("Entity Type?: ${handle.integers.values}")
////                Bukkit.broadcastMessage("Entity UUID: ${handle.uuiDs.read(0)}")
////                Bukkit.broadcastMessage("X: ${handle.doubles.read(0)}")
////                Bukkit.broadcastMessage("Y: ${handle.doubles.read(1)}")
////                Bukkit.broadcastMessage("Z: ${handle.doubles.read(2)}")
//        Bukkit.broadcastMessage("integers: ${handle.integers.values}")
//        Bukkit.broadcastMessage("doubles: ${handle.doubles.values}")
//        Bukkit.broadcastMessage("bytes: ${handle.bytes.values}")
//        Bukkit.broadcastMessage("shorts: ${handle.shorts.values}")
//        Bukkit.broadcastMessage("intLists: ${handle.intLists.values}")
//        Bukkit.broadcastMessage("integerArrays: ${handle.integerArrays.values}")
//        Bukkit.broadcastMessage("entityTypeModifier: ${handle.entityTypeModifier.values}")
//
//
//    }
//})