package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType

data class RoomStateMessage(
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val channel: String,
    val roomId: String,
    val state: Map<String, Int>,
    override val type: MessageType,
):IMessage
