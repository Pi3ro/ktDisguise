package me.pi3ro.disguise.listeners

import me.pi3ro.disguise.DisguiseAPI
import me.pi3ro.disguise.DisguiseAPI.ranks
import me.pi3ro.disguise.DisguiseAPI.names
import me.pi3ro.disguise.DisguisePlugin
import me.pi3ro.disguise.utils.CC.translate
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

class DisguiseListener(private val plugin: DisguisePlugin) : Listener
{
    @EventHandler
    fun onAsyncLoginEvent(event: AsyncPlayerPreLoginEvent)
    {
        val name = event.name
        val players = plugin.server.onlinePlayers
            .filter { it.name.equals(name, ignoreCase = true) }

        players.forEach { player ->
            player.kickPlayer(translate("&cUndisguised."))
        }
    }

    @EventHandler
    fun onDisguiseChat(event: AsyncPlayerChatEvent)
    {
        val player = event.player

        if (!DisguiseAPI.isDisguised(player)) return

        val msg = event.message.replace("%", "%%")
        val rank = ranks[player.uniqueId]

        val format = mapOf(
            "Default" to "&a${player.name}&7: &f$msg",
            "Famous" to "&d${player.name}&7: &f$msg",
            "Silver" to "&7${player.name}&7: &f$msg",
            "Gold" to "&6${player.name}&7: &f$msg",
            "Diamond" to "&b${player.name}&7: &f$msg",
            "Rubi" to "&c${player.name}&7: &f$msg",
            "Blue" to "&9${player.name}&7: &f$msg"
        ).getOrDefault(rank, "&a${player.name}&7: &f$msg")

        event.format = translate(format)
    }

    @EventHandler
    fun onQuitEvent(event: PlayerQuitEvent)
    {
        val player = event.player

        ranks.remove(player.uniqueId)
        names.remove(player.uniqueId)
    }
}
