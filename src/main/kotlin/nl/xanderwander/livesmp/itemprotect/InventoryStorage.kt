package nl.xanderwander.livesmp.itemprotect

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.bukkit.entity.Player
import org.bukkit.inventory.PlayerInventory
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class InventoryStorage {

    private val path = "plugins/LiveSMP/Inventories/"
    private val serializer = InventorySerializer()
    private val hasInventoryStored = hashMapOf<Player, Boolean>()

    fun hasInventoryStored(player: Player): Boolean {
        return if (hasInventoryStored.containsKey(player)) hasInventoryStored[player] ?: false else false
    }

    fun storeInventory(player: Player) {
        val inv = serializer.playerInventoryToBase64(player.inventory)
        val filePath = path + player.uniqueId
        File(path).mkdirs()
        File(filePath).delete()
        val writer = FileWriter(filePath)
        Gson().toJson(inv, writer)
        writer.flush()
        writer.close()
        hasInventoryStored[player] = true
    }

    fun loadInventory(player: Player) {
        val filePath = path + player.uniqueId
        val file = File(filePath)
        if (!file.exists()) return
        val reader = FileReader(file)
        val type = object : TypeToken<String>() {}.type
        val inv: String = Gson().fromJson(reader, type)
        serializer.playerInventoryFromBase64(inv, player.inventory)
        hasInventoryStored[player] = false
    }

}