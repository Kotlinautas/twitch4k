package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.Reply
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType
import java.util.Date

data class PrivateIMessage(
    val id: String,
    val user: User,
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val channel: String,
    val roomId: String,
    val time: Date,
    val emotes: List<Emote>,
    val bits: Int,
    val action: Boolean,
    val firstMessage: Boolean,
    val reply: Reply,
    val customRewardId: String,
    override val type: MessageType,
) : IMessage
