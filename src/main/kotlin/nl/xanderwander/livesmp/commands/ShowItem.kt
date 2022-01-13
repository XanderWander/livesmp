package nl.xanderwander.livesmp.commands

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import nl.xanderwander.livesmp.Main
import nl.xanderwander.livesmp.modules.PlayerModule
import nl.xanderwander.livesmp.utils.ItemUtils
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShowItem: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender.hasPermission("level.admin") && sender is Player) {

            var message = ""
            if (args.isNotEmpty()) {
                for (arg in args) {
                    message = "$message $arg"
                }
            }

            val item = sender.inventory.getItem(sender.inventory.heldItemSlot) ?: return noItem(sender)
            if (item.type == Material.AIR) return noItem(sender)
            val prefix = Main.luckPermsHook.playerPrefix(sender)
            val msg = "$prefix §7${sender.name} §9»§f$message"
            PlayerModule.all().forEach { it.sendMessage(msg) }
            showItem(item)

        }
        return true
    }

    private fun noItem(player: Player): Boolean {
        player.sendMessage("§cHou een item in je hand om dit command te gebruiken")
        return true
    }

    private fun showItem(item: ItemStack) {

        val itemName = item.itemMeta?.displayName ?: ""
        val text = if (itemName == "") {
            TextComponent("[ ${ItemUtils.formatTypeAsName(item.type)}§r ]")
        } else {
            TextComponent("[ §b§o$itemName§r ]")
        }

        try {
            val nms = Main.instance.server.javaClass.getPackage().name.split(".")[3]
            val craftItemStackClass = Class.forName("org.bukkit.craftbukkit.$nms.inventory.CraftItemStack")
            val itemStack = craftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack::class.java).invoke(null, item)
            val itemStackClass = itemStack::class.java
            val nbtTagCompound = itemStackClass.getDeclaredMethod("s").invoke(itemStack)
            var jsonData = "{}"
            if (nbtTagCompound != null) {
                val nbtTagCompoundClass = nbtTagCompound::class.java
                jsonData = nbtTagCompoundClass.getDeclaredMethod("toString").invoke(nbtTagCompound) as String
            }
            val hoverEventComponents = arrayOf<BaseComponent>(
                TextComponent("{id:\"minecraft:${item.type.toString().lowercase()}\",Count:${item.amount}b,tag:$jsonData}")
            )
            @Suppress("DEPRECATION")
            text.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents)

        } catch (e: Exception) {
            text.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text("Failed to load item."))
            e.printStackTrace()
        }
        val message = TextComponent("§cItem Showcase §9» §f")
        message.addExtra(text)
        PlayerModule.all().forEach { it.spigot().sendMessage(ChatMessageType.CHAT, message) }

    }

}