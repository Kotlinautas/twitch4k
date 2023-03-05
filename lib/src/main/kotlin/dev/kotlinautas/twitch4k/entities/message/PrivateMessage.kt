package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.Reply
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.util.MessageType
import java.util.Date

data class PrivateMessage(
    val raw:String,
    val type: MessageType,
    val rawType: String,
    val tags: Map<String, String>,
    val channel: String,
    val text: String,
    val roomId: String,
    val id: String,
    val date: Date,
    val emotes: List<Emote>,
    val bits: Int,
    val firstMessage: Boolean,
    val reply: Reply?,
    val user: User
)
