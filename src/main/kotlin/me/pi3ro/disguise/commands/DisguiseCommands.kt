package me.pi3ro.disguise.commands

import me.pi3ro.disguise.DisguiseAPI
import me.pi3ro.disguise.utils.CC.translate
import me.pi3ro.disguise.utils.generator.Generator
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Optional
import revxrsal.commands.bukkit.annotation.CommandPermission

class DisguiseCommands
{
    @Command("disguise", "d")
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
}
