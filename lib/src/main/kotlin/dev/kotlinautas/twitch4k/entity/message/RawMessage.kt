package dev.kotlinautas.twitch4k.entity.message

import dev.kotlinautas.twitch4k.util.MessageType

data class RawMessage(
    val raw: String,
    val tags: Map<String, String>,
    val prefix: String,
    val command: String,
    val params: List<String>
) {
    fun toPingMessage() = PingMessage(raw, MessageType.PING, params.first())
}
