package me.pi3ro.disguise

import dev.iiahmed.disguise.DisguiseManager
import dev.iiahmed.disguise.DisguiseProvider
import me.pi3ro.disguise.handler.Handler
import org.bukkit.plugin.java.JavaPlugin

class DisguisePlugin : JavaPlugin()
{
    var provider: DisguiseProvider = DisguiseManager.getProvider()

    companion object
    {
        lateinit var INSTANCE: DisguisePlugin
    }

    override fun onEnable()
    {
        INSTANCE = this

        DisguiseManager.setPlugin(this)
        Handler(this)
    }
}