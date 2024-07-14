package me.pi3ro.disguise.utils.menu

import me.pi3ro.disguise.utils.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player
import java.util.*

object MenuController
{
    var menuMap = hashMapOf<UUID, Menu>()
    var paginatedMenuMap = hashMapOf<UUID, PaginatedMenu>()


    fun addToMenuMap(player: Player, menu: Menu)
    {
        menuMap[player.uniqueId] = menu
    }

}