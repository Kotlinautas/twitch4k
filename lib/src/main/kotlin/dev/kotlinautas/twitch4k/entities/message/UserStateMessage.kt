package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType

data class UserStateMessage(
    val user: User,
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val channel: String,
    val emotes: List<Emote>,
    override val type: MessageType
): IMessage
