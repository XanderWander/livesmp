package nl.xanderwander.livesmp.events

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.meta.Damageable

class ToolProtect: Listener {

    @EventHandler
    fun onToolBreak(event: BlockBreakEvent) {
        val p = event.player
        val tool = p.inventory.getItem(p.inventory.heldItemSlot) ?: return
        val type = tool.type.toString().lowercase()
        if (type.contains("axe") || type.contains("shovel") || type.contains("hoe")) {
            if (type.contains("diamond") || type.contains("netherite")) {
                val meta = (tool.itemMeta ?: return) as Damageable
                val durability = tool.type.maxDurability - meta.damage
                if (durability <= 10) {
                    p.spigot().sendMessage(
                        ChatMessageType.ACTION_BAR,
                        TextComponent("${ChatColor.RED}Je tool gaat bijna kapot!")
                    )
                    p.playSound(p.location, Sound.ENTITY_ITEM_BREAK, 1f, 1f)
                }
            }
        }
    }

}