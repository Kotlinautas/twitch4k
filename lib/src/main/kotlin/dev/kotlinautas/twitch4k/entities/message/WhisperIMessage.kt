package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType

data class WhisperIMessage(
    val user: User,
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val target: String,
    val messageId: String,
    val threadID: String,
    val emotes: List<Emote>,
    val action: Boolean,
    override val type: MessageType,
) : IMessage