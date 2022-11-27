package dev.kotlinautas.twitch4k.entity

import java.util.*

data class ChatMessage(
    val id: String,
    val date: Date,
    val channel: String,
    val text: String,
    val user: User,
)
