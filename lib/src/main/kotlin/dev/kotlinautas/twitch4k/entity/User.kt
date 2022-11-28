package dev.kotlinautas.twitch4k.entity

data class User(
    val id: Long,
    val displayName: String,
    val isSubscriber: Boolean,
    val isModerator: Boolean,
    val isFirstMessage: Boolean,
)
