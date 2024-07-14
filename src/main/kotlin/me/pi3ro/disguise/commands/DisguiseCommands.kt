package me.pi3ro.disguise.commands

import me.pi3ro.disguise.DisguiseAPI
import me.pi3ro.disguise.menus.DisguisedListMenu
import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.generator.Generator
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Cooldown
import revxrsal.commands.annotation.Optional
import revxrsal.commands.bukkit.annotation.CommandPermission
import java.util.concurrent.TimeUnit

class DisguiseCommands
{
    @Command("disguise", "d")
    @Cooldown(value = 3, unit = TimeUnit.MINUTES)
    @CommandPermission("disguise.command.disguise")
    fun onApply(player: Player, @Optional name: String?, @Optional skin: String?)
    {
        if (DisguiseAPI.isDisguised(player))
        {
            player.sendMessage(translate("&cYou're already disguised."))
            return
        }

        if (name != null && !DisguiseAPI.isValid(name))
        {
            player.sendMessage(translate("&cDisguise name is invalid."))
            return
        }

        DisguiseAPI.apply(
            player,
            name ?: Generator.apply(),
            skin ?: Generator.apply()
        )
    }

    @Command("undisguise")
    @CommandPermission("disguise.command.undisguise")
    fun onUndo(player: Player)
    {
        if (!DisguiseAPI.isDisguised(player))
        {
            player.sendMessage(translate("&cYou're not disguised."))
            return
        }

        DisguiseAPI.undo(player)
    }

    @Command("disguisecheck")
    @CommandPermission("disguise.command.disguisecheck")
    fun onCheck(player: Player, target: Player)
    {
        if (!DisguiseAPI.isDisguised(player))
        {
            player.sendMessage(translate("&cThis player is not disguised."))
            return
        }

        val info = listOf(
            "",
            "&b&lDisguise Information",
            "",
            "&7► &fReal Name: &a${DisguiseAPI.getRealName(target)}",
            "&7► &fDisguised as: &b${DisguiseAPI.getDisguisedName(target)}",
            "",
            "&a&lTHIS PLAYER IS CURRENTLY DISGUISED.",
            ""
        )

        info.forEach { player.sendMessage(translate(it)) }
    }

    @Command("disguiselist")
    @CommandPermission("disguise.command.disguiselist")
    fun onList(player: Player)
    {
        DisguisedListMenu(player).updateMenu()
    }
}
