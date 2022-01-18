package nl.xanderwander.livesmp.itemprotect

import nl.xanderwander.livesmp.Main
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException


class InventorySerializer {

    @Throws(IllegalStateException::class)
    fun playerInventoryToBase64(inventory: PlayerInventory): String {
        return toBase64(inventory)
    }

    @Throws(IllegalStateException::class)
    fun playerInventoryFromBase64(data: String, inventory: PlayerInventory) {
        fromBase64(data, inventory)
    }

    @Throws(IllegalStateException::class)
    fun toBase64(inventory: Inventory): String {
        return try {
            val air = ItemStack(Material.AIR)
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeInt(inventory.size)

            for (i in 0 until inventory.size) {
                dataOutput.writeObject(inventory.getItem(i))
                inventory.setItem(i, air)
            }

            dataOutput.close()
            Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Unable to save item stacks.", e)
        }
    }

    @Throws(IOException::class)
    fun fromBase64(data: String, inventory: PlayerInventory) {
        try {
            val air = ItemStack(Material.AIR)
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)

            for (i in 0 until dataInput.readInt()) {
                val item = dataInput.readObject()
                if (item != null) {
                    inventory.setItem(i, item as ItemStack)
                    Main.instance.logger.info("Loaded item ${item.type.name.lowercase()} at slot $i")
                } else {
                    inventory.setItem(i, air)
                }
            }
            dataInput.close()
        } catch (e: ClassNotFoundException) {
            throw IOException("Unable to decode class type.", e)
        }
    }

}