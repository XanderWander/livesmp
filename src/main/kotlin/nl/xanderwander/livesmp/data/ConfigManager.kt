package nl.xanderwander.livesmp.data

import nl.xanderwander.livesmp.Main
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.InputStream
import java.io.OutputStream

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

    fun resetConfig() {

        val classLoader = ClassLoader.getSystemClassLoader()
        val inputStream = classLoader.getResourceAsStream("/config.yml")
        val file = safeFile("plugins/LiveSMP", "config.yml")

        if (inputStream == null) {
            Bukkit.broadcastMessage("Inputstream is null")
            return
        }

        file.copyInputStreamToFile(inputStream)

    }

    private fun File.copyInputStreamToFile(inputStream: InputStream) {
        this.outputStream().use { fileOut ->
            inputStream.copyTo(fileOut)
        }
    }

    private fun safeFile(folder: String, file: String): File {
        File(folder).mkdirs()
        return File(file)
    }

}