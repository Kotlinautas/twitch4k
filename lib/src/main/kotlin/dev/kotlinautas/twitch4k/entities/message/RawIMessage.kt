package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType

data class RawIMessage(
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    override val type: MessageType,
) : IMessage
