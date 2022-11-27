package dev.kotlinautas.twitch4k.entity

data class User (
    val id: Int,
    val displayName:String,
    val isSubscriber: Boolean,
    val isModerator: Boolean,
    val isFirstMessage: Boolean,
)
