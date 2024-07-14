package me.pi3ro.disguise.menus

import me.pi3ro.disguise.DisguiseAPI
import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.ItemBuilder
import me.pi3ro.disguise.utils.menu.Button
import me.pi3ro.disguise.utils.menu.pagination.PaginatedMenu
import me.pi3ro.disguise.utils.menu.skull.SkullUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class DisguisedListMenu(private val player: Player) :
    PaginatedMenu(18, player)
{
    override fun getTitle(player: Player): String
    {
        return translate("&7Disguise List")
    }

    override fun getPagesButtons(player: Player): MutableMap<Int, Button>
    {
        return Bukkit.getOnlinePlayers()
            .filter(DisguiseAPI::isDisguised)
            .mapIndexed { index, disguise -> index to DisguisedButton(disguise) }
            .toMap()
            .toMutableMap()
    }

    private class DisguisedButton(val player: Player) : Button()
    {
        override fun getMaterial(player: Player): Material
        {
            return Material.PAPER
        }

        override fun getDescription(player: Player): MutableList<String>?
        {
            val desc = listOf(
                "&7&m-------------------",
                "&fReal Name: &b${DisguiseAPI.getRealName(player)}",
                "&fFake Name: &b${DisguiseAPI.getDisguisedName(player)}",
                "",
                "&eClick to undo disguise.",
                "&7&m-------------------",
                )

            return desc.map { translate(it) }.toMutableList()
        }

        override fun getDisplayName(player: Player): String?
        {
            return translate("&b${DisguiseAPI.getDisguisedName(player)}")
        }

        override fun getData(player: Player): Short
        {
            return 0
        }

        override fun onClick(player: Player, slot: Int, type: ClickType)
        {
            DisguiseAPI.undo(this.player)

            player.sendMessage(translate("&aSuccessfully undisguised. &7(${this.player.name})"))
        }

        override fun getButtonItem(player: Player): ItemStack?
        {
            val item = SkullUtil.generate(player.name, player.name)

            return ItemBuilder(item).name(getDisplayName(player)).setLore(getDescription(player)!!).build()
        }
    }
}