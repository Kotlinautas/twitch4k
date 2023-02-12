package dev.kotlinautas.twitch4k.entities

import dev.kotlinautas.twitch4k.entities.EmotePosition

data class Emote(
    val name: String,
    val id: String,
    val count: Int,
    val emotePositions: List<EmotePosition>
)
