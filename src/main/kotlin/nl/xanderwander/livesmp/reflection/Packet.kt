package nl.xanderwander.livesmp.reflection

import com.comphenix.protocol.events.PacketContainer
import nl.xanderwander.livesmp.Main
import org.bukkit.entity.Player
import java.lang.reflect.InvocationTargetException


abstract class Packet(val handle: PacketContainer) {
    fun send(receiver: Player) {
        try {
            Main.manager.sendServerPacket(receiver, handle)
        } catch (e: InvocationTargetException) {}
    }
    fun send(receivers: ArrayList<Player>) {
        receivers.forEach { send(it) }
    }
    fun send(receivers: MutableSet<Player>) {
        receivers.forEach { send(it) }
    }
}