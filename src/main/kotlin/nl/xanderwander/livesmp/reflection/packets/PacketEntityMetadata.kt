package nl.xanderwander.livesmp.reflection.packets

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.reflection.Packet
import org.bukkit.inventory.ItemStack

class PacketEntityMetadata(entity: Int, item: ItemStack) :
    Packet(Main.manager.createPacket(PacketType.Play.Server.ENTITY_METADATA)) {

    init {
        val dataWatcher = WrappedDataWatcher()
        val stackSerializer = WrappedDataWatcher.Registry.getItemStackSerializer(false)
        dataWatcher.setObject(8, stackSerializer, item)
        handle.integers.write(0, entity)
        handle.watchableCollectionModifier.write(0, dataWatcher.watchableObjects)
    }

}