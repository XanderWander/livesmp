package nl.xanderwander.livesmp.utils

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ItemUtils {

    companion object {

        fun newItem(material: Material, name: String = "", lore: ArrayList<String> = arrayListOf(), modelData: Int = -1, glowing: Boolean = false): ItemStack {

            val stack = ItemStack(material)
            val meta = stack.itemMeta!!
            if (name != "") meta.setDisplayName(name)
            if (lore != arrayListOf<String>()) meta.lore = lore
            if (modelData >= 0) meta.setCustomModelData(modelData)
            if (glowing) {
                meta.addEnchant(Enchantment.DURABILITY, 1, false)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
            }
            stack.itemMeta = meta
            return stack
        }

        fun formatTypeAsName(material: Material): String {
            return StringUtils.capitalizeFirstLetters(material.name.replace("_", " "))
        }

    }

}