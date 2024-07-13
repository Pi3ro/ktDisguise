package me.pi3ro.disguise.utils

import org.bukkit.ChatColor

object CC
{
    fun translate(msg: String?): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }
}