package me.pi3ro.disguise.listeners

import me.pi3ro.disguise.DisguisePlugin
import me.pi3ro.disguise.utils.CC.translate
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

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
}
