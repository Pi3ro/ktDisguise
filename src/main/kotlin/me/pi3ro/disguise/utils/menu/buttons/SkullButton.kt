package me.pi3ro.disguise.utils.menu.buttons

import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.menu.Button
import me.pi3ro.disguise.utils.menu.skull.SkullUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class SkullButton(
    val texture: String,
    val description: MutableList<String>,
    val name: String,
) : Button()
{
    var body: ((Player, Int, ClickType) -> Unit)? = null

    fun setBody(body: ((Player, Int, ClickType) -> Unit)?): SkullButton
    {
        return this.apply { this.body = body }
    }

    override fun getButtonItem(player: Player): ItemStack?
    {
        val itemstack = ItemStack(Material.SKULL_ITEM)

        itemstack.durability = 3

        val itemMeta = itemstack.itemMeta as SkullMeta

        itemMeta.displayName = translate(name)
        itemMeta.lore = description

        return SkullUtil.applyCustomHead(itemstack, texture, "98pingIsCoolAsf", description, name)
    }

    override fun getMaterial(player: Player): Material
    {
        return Material.SKULL_ITEM
    }

    override fun getDescription(player: Player): MutableList<String>
    {
        return description
    }

    override fun getDisplayName(player: Player): String
    {
        return translate(name)
    }

    override fun getData(player: Player): Short
    {
        return 3
    }

    override fun onClick(player: Player, slot: Int, type: ClickType)
    {
        if (body != null)
        {
            body?.invoke(player, slot, type)
        }
    }
}