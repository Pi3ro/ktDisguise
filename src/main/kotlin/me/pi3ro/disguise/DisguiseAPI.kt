package me.pi3ro.disguise

import dev.iiahmed.disguise.Disguise
import dev.iiahmed.disguise.DisguiseProvider
import dev.iiahmed.disguise.DisguiseResponse
import me.pi3ro.disguise.utils.CC.translate
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.UUID
import kotlin.collections.HashMap

object DisguiseAPI
{
    private val provider: DisguiseProvider
        get() = DisguisePlugin.INSTANCE.provider

    val names: MutableMap<UUID, String> = HashMap()
    val ranks: MutableMap<UUID, String> = HashMap()
    var picking: MutableMap<UUID, Boolean> = HashMap()

    fun apply(player: Player, name: String, skin: String)
    {
        val disguise = try
        {
            Disguise.builder()
                .setName(name)
                .setSkin(skin)
                .setEntityType(EntityType.PLAYER)
                .build()
        } catch (e: Exception) {
            Disguise.builder()
                .setName(name)
                .setSkin("Notch")
                .setEntityType(EntityType.PLAYER)
                .build()
        }

        when (val response = provider.disguise(player, disguise))
        {
            DisguiseResponse.SUCCESS -> {
                player.sendMessage(translate("&aSuccess! You now look like &6$name!"))
                player.sendMessage(translate("&c$name is an existing Minecraft player, so if they log in for the first time as you're disguised, you will be kicked."))
            }
            DisguiseResponse.FAIL_NAME_ALREADY_ONLINE -> player.sendMessage(translate("&cThere's already an online player with that name."))
            else -> player.sendMessage(translate("&cDisguise is unsuccessful with the reason: ${response.name}"))
        }
    }

    fun undo(player: Player)
    {
        provider.undisguise(player)
        ranks.remove(player.uniqueId)
        names.remove(player.uniqueId)
        player.sendMessage(translate("&aSuccessfully undisguised."))
    }

    fun isDisguised(player: Player): Boolean
    {
        return provider.isDisguised(player)
    }

    fun getRealName(player: Player): String
    {
        return provider.getInfo(player).name
    }

    fun getDisguisedName(player: Player): String
    {
        return provider.getInfo(player).nickname
    }

    fun isValid(name: String): Boolean
    {
        val chars
        = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_"
        return name.length in 3..16 && name.all { it in chars }
    }
}