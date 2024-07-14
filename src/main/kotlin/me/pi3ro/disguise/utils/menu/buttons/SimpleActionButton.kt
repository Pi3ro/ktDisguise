package me.pi3ro.disguise.utils.menu.buttons

import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.menu.Button
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType

class SimpleActionButton(
    val material: Material,
    val description: MutableList<String>,
    val name: String, val data: Short,
) : Button()
{
    var body: ((Player, Int, ClickType) -> Unit)? = null
    fun setBody(body: ((Player, Int, ClickType) -> Unit)?): SimpleActionButton
    {
        return this.apply { this.body = body }
    }

    override fun getMaterial(player: Player): Material
    {
        return material
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
        return data
    }

    override fun onClick(player: Player, slot: Int, type: ClickType)
    {
        if (body != null)
        {
            body?.invoke(player, slot, type)
        }
    }
}