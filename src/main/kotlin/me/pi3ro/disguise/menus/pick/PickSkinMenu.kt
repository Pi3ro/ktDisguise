package me.pi3ro.disguise.menus.pick

import me.pi3ro.disguise.DisguiseAPI
import me.pi3ro.disguise.DisguiseAPI.names
import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.SkullTexture
import me.pi3ro.disguise.utils.generator.Generator
import me.pi3ro.disguise.utils.menu.Button
import me.pi3ro.disguise.utils.menu.buttons.SkullButton
import me.pi3ro.disguise.utils.menu.buttons.SkullButtonOnlyName
import me.pi3ro.disguise.utils.menu.pagination.PaginatedMenu
import org.bukkit.entity.Player

class PickSkinMenu(private val player: Player) :
    PaginatedMenu(27, player)
{
    override fun getHeaderItems(player: Player): MutableMap<Int, Button>
    {
        val buttons = mutableMapOf<Int, Button>()

        buttons[1] = SkullButtonOnlyName(
            player.name,
            "&9Your Own",
            mutableListOf(),
        ).setBody { player, i, clickType ->
            DisguiseAPI.apply(
                player,
                names[player.uniqueId] ?: Generator.apply(),
                player.name
            )
        }
        buttons[2] = SkullButton(
            SkullTexture.RANDOM.value,
            mutableListOf(),
            "&9Random"
        ).setBody { player, i, clickType ->
            DisguiseAPI.apply(
                player,
                names[player.uniqueId] ?: Generator.apply(),
                Generator.apply()
            )
        }
        buttons[5] = SkullButtonOnlyName(
            names[player.uniqueId]!!,
            "&9${names[player.uniqueId]}",
            mutableListOf(),
        ).setBody { player, i, clickType ->
            DisguiseAPI.apply(
                player,
                names[player.uniqueId] ?: Generator.apply(),
                names[player.uniqueId] ?: Generator.apply(),
            )
        }
        buttons[6] = SkullButton(
            SkullTexture.STEVE.value,
            mutableListOf(),
            "&9Steve"
        ).setBody { player, i, clickType ->
            DisguiseAPI.apply(
                player,
                names[player.uniqueId] ?: Generator.apply(),
                "MHF_Steve"
            )
        }
        buttons[7] = SkullButton(
            SkullTexture.ALEX.value,
            mutableListOf(),
            "&9Alex"
        ).setBody { player, i, clickType ->
            DisguiseAPI.apply(
                player,
                names[player.uniqueId] ?: Generator.apply(),
                "MHF_Alex"
            )
        }

        return buttons
    }

    override fun getPagesButtons(player: Player): MutableMap<Int, Button>
    {
        val buttons = mutableMapOf<Int, Button>()


        return buttons
    }

    override fun getTitle(player: Player): String
    {
        return translate("&7Pick a skin")
    }
}