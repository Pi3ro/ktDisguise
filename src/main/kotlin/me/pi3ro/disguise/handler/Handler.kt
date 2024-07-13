package me.pi3ro.disguise.handler

import me.pi3ro.disguise.DisguisePlugin
import me.pi3ro.disguise.commands.DisguiseCommands
import me.pi3ro.disguise.listeners.DisguiseListener
import org.bukkit.event.Listener
import revxrsal.commands.bukkit.BukkitCommandHandler

class Handler(private val plugin: DisguisePlugin)
{
    private val handler: BukkitCommandHandler = BukkitCommandHandler.create(plugin)

    init
    {
        loadCommands()
        loadListeners()
    }

    private fun loadCommands()
    {
        handler.register(DisguiseCommands())
    }

    private fun loadListeners()
    {
        val listeners: List<Listener> =
            listOf(
                DisguiseListener(plugin)
            )

        listeners.forEach { listener ->
            plugin.server.pluginManager.registerEvents(listener, plugin)
        }
    }
}