package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType
import java.util.*

data class UserNoticeMessage (
    val user: User,
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val channel: String,
    val roomId: String,
    val id: String,
    val time: Date,
    val emotes: List<Emote>,
    val messageId: String,
    val messageParams: Map<String, String>,
    val systemMessage: String,
    override val type: MessageType
): IMessage