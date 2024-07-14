package me.pi3ro.disguise.handler

import me.pi3ro.disguise.DisguisePlugin
import me.pi3ro.disguise.commands.DisguiseCommands
import me.pi3ro.disguise.listeners.DisguiseListener
import me.pi3ro.disguise.utils.menu.listener.MenuListener
import me.pi3ro.disguise.utils.menu.update.MenuAutoUpdate
import org.bukkit.event.Listener
import revxrsal.commands.bukkit.BukkitCommandHandler

class Handler(private val plugin: DisguisePlugin)
{
    private val handler: BukkitCommandHandler = BukkitCommandHandler.create(plugin)

    init
    {
        loadCommands()
        loadListeners()

        MenuAutoUpdate().runTaskTimer(plugin, 20L, 20L)
    }

    private fun loadCommands()
    {
        handler.register(DisguiseCommands())
    }

    private fun loadListeners()
    {
        val listeners: List<Listener> =
            listOf(
                DisguiseListener(plugin),
                MenuListener(),
            )

        listeners.forEach { listener ->
            plugin.server.pluginManager.registerEvents(listener, plugin)
        }
    }
}