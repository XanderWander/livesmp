package nl.xanderwander.livesmp.reflection.packets

import com.comphenix.protocol.PacketType
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.objects.Location
import nl.xanderwander.livesmp.reflection.Packet
import org.bukkit.entity.EntityType
import java.util.*

class PacketSpawnEntity(entityID: Int, location: Location) :
    Packet(Main.manager.createPacket(PacketType.Play.Server.SPAWN_ENTITY)) {

    init {
        handle.integers
            .write(0, entityID)
        handle.uuiDs
            .write(0, UUID.randomUUID())
        handle.doubles
            .write(0, location.x)
            .write(1, location.y)
            .write(2, location.z)
        handle.entityTypeModifier
            .write(0, EntityType.DROPPED_ITEM)
    }

}