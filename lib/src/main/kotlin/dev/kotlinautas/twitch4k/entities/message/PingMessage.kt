package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.util.MessageType

data class PingMessage(
    val raw: String,
    val type: MessageType,
    val rawType: String,
    val message: String
)