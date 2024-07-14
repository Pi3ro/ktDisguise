package me.pi3ro.disguise.menus.pick

import me.pi3ro.disguise.DisguiseAPI.ranks
import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.ItemBuilder
import me.pi3ro.disguise.utils.menu.Button
import me.pi3ro.disguise.utils.menu.Menu
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class PickRankMenu(private val player: Player) : Menu(player)
{
    override fun getButtons(player: Player): MutableMap<Int, Button>
    {
        val buttons = mutableMapOf<Int, Button>()

        buttons[1] = RankButton("Default", Color.LIME)
        buttons[3] = RankButton("Famous", Color.FUCHSIA)
        buttons[5] = RankButton("Silver", Color.SILVER)
        buttons[7] = RankButton("Gold", Color.ORANGE)
        buttons[10] = RankButton("Diamond", Color.AQUA)
        buttons[12] = RankButton("Rubi", Color.RED)
        buttons[14] = RankButton("Blue", Color.BLUE)

        return buttons
    }

    override fun getTitle(player: Player): String
    {
        return translate("&7Pick a rank")
    }

    private class RankButton(val name: String, val color: Color) : Button()
    {
        override fun getMaterial(player: Player): Material
        {
            return Material.STONE
        }

        override fun getDescription(player: Player): MutableList<String>?
        {
            val desc = listOf(
                "&7&m-------------------",
                "",
                "&7&m-------------------",
            )

            return desc.map { translate(it) }.toMutableList()
        }

        override fun getDisplayName(player: Player): String?
        {
            return translate("&e$name")
        }

        override fun getData(player: Player): Short
        {
            return 0
        }

        override fun onClick(player: Player, slot: Int, type: ClickType)
        {
            ranks[player.uniqueId] = name

            PickSkinMenu(player).updateMenu()
        }

        override fun getButtonItem(player: Player): ItemStack?
        {
            return ItemBuilder(Material.LEATHER_CHESTPLATE).
            name(getDisplayName(player)).
            setLeatherArmorColor(color).
            build()
        }
    }

    override fun size(buttons: Map<Int, Button>): Int
    {
        return 18
    }
}