package nl.xanderwander.livesmp.events

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.util.*

class PrepareAnvil: Listener {

    @EventHandler
    fun onAnvil(event: PrepareAnvilEvent) {

        val inv = event.inventory
        val i0 = inv.getItem(0)
        val i1 = inv.getItem(1)
        val items = arrayListOf(Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE)
        val type = i1?.type ?: return

        if (i0?.type != Material.ELYTRA) return
        if (!items.contains(type)) return

        val index = items.indexOf(type)
        val item = ItemStack(Material.ELYTRA)
        val meta = item.itemMeta ?: return
        val an = AttributeModifier.Operation.ADD_NUMBER
        val ec = EquipmentSlot.CHEST

        val ga = AttributeModifier(UUID.randomUUID(), "generic_armor", if (index == 0) 6.0 else 8.0, an, ec)
        val gat = AttributeModifier(UUID.randomUUID(), "generic_armor_toughness", if (index == 1) 2.0 else 3.0, an, ec)
        val gkr = AttributeModifier(UUID.randomUUID(), "generic_knockback_resistance", 0.1, an, ec)

        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, ga)
        inv.repairCost = 20

        if (index > 0) {
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, gat)
            inv.repairCost = 30
        }
        if (index > 1) {
            meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, gkr)
            inv.repairCost = 39
        }

        meta.setDisplayName(i0.itemMeta?.displayName ?: "")
        item.itemMeta = meta
        item.addEnchantments(i0.itemMeta?.enchants ?: mapOf())
        event.result = item

    }

}