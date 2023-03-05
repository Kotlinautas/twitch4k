package dev.kotlinautas.twitch4k.entity.message

import dev.kotlinautas.twitch4k.util.MessageType
import java.util.Date

data class PrivateMessage(
    val raw: String,
    val type: MessageType,
    val tags: Map<String, String>,
    val channel: String,
    val text: String,
    val roomId: String,
    val date: Date,
    val emotes: List<Emote>,
    val bits: Int,
    val isFirstMessage: Boolean,
    val reply: Reply?,
    val user: User
)
