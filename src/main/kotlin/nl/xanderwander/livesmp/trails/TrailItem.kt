package nl.xanderwander.livesmp.trails

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.objects.Vector
import nl.xanderwander.livesmp.reflection.packets.PacketEntityDestroy
import nl.xanderwander.livesmp.reflection.packets.PacketEntityMetadata
import nl.xanderwander.livesmp.reflection.packets.PacketSpawnEntity
import nl.xanderwander.livesmp.utils.RunnableUtils
import org.bukkit.Location
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class TrailItem(private val item: ItemStack) {



    fun spawn(loc: Location) {
        val id = Random.nextInt()
        val players = PlayerModule.all()
        PacketSpawnEntity(id, nl.xanderwander.livesmp.objects.Location(loc)).send(players)
        PacketEntityMetadata(id, item).send(players)
        Main.trailManager.removeIDS.add(id)
        RunnableUtils.runLater(50L) {
            PacketEntityDestroy(id).send(players)
            Main.trailManager.removeIDS.remove(id)
        }
    }

}