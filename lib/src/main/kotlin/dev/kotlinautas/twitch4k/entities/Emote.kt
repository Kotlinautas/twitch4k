package dev.kotlinautas.twitch4k.entities


data class Emote(
    val name:String,
    val id:String,
    val count: Int,
    val positions: List<Position>
)
