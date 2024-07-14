package me.pi3ro.disguise.utils.generator

import kotlin.random.Random

object Generator
{
    private val random = Random.Default
    val names = listOf(
        "sewerratss", "Luukiee", "isuccdeekforlife", "Juliantjecool", "Chealex",
        "Alekser", "Kopiko_", "xXLupusXx", "Mxony142", "FryPanMan", "Bot539",
        "AntiChestnuts", "Swede_Tomate", "nxcky_ab", "BeetleElf898786", "Ghost_shadow04",
        "Fanni225", "I_Gir3l_I", "zyrosa", "darl", "TheRizzardKing", "Ohih", "FnyRaks",
        "PostNut", "wozzizz", "Filiipkos", "Oskar_Sutter_Pik", "HampusNX", "Eggorollers",
        "TS_Shawarma", "LuvvLuna", "Praqte", "SadHonig", "hemmylol", "NxtG3n3ration",
        "Peypoots", "Fistaszjoo", "stezui", "mikell0", "7l2", "MrRandyHacks", "Lavatales",
        "hoesmad", "Alfapedro", "Biskwiq", "ImHacks", "Config", "capsy", "levens",
        "Protectionism", "sooosa"
    )

    fun apply(): String = names.random(random)
}
