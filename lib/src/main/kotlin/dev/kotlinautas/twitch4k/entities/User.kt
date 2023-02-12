package dev.kotlinautas.twitch4k.entities

data class User(
    val id: String,
    val name: String,
    val displayName: String,
    val color: String,
    val badges: Map<String, Int>
)
