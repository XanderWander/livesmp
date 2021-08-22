package nl.xanderwander.livesmp.data

import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.utils.RunnableHelper
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.InputStream
import java.io.OutputStream

class ConfigManager {

    private val file = File("plugins/LiveSMP/config.yml")
    private val config: YamlConfiguration

    init {
        if (!file.exists()) Main.instance.resetConfig()
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun mainConfig(): YamlConfiguration {
        return config
    }

    fun safeFile(folder: String, file: String): File {
        File(folder).mkdirs()
        return File(file)
    }

}