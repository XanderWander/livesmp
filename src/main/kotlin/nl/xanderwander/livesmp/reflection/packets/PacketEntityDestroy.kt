package nl.xanderwander.livesmp.reflection.packets

import com.comphenix.protocol.PacketType
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.reflection.Packet

class PacketEntityDestroy(entityID: Int) : Packet(Main.manager.createPacket(PacketType.Play.Server.ENTITY_DESTROY)) {

    init {
        handle.intLists.write(0, arrayListOf(entityID))
    }

}