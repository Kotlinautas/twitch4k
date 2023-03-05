package dev.kotlinautas.twitch4k.entity.message

import dev.kotlinautas.twitch4k.util.MessageType


data class PingMessage(
    val raw: String,
    val type: MessageType,
    val param: String
)
