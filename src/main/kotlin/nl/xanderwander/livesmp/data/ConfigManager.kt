package nl.xanderwander.livesmp.data

import nl.xanderwander.livesmp.Main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigManager {

    private val file = File("plugins/LiveSMP/config.yml")
    private val config: YamlConfiguration

    init {
        if (!file.exists()) resetConfig()
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun mainConfig(): YamlConfiguration {
        return config
    }

    private fun resetConfig() {
        File(Main.instance::class.java.getResource("config.yml").file).copyTo(safeFile("plugins/LiveSMP", "config.yml"))
    }

    private fun safeFile(folder: String, file: String): File {
        File(folder).mkdirs()
        return File(file)
    }

}