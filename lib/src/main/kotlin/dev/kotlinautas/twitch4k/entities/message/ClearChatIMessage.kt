package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.interfaces.IMessage
import dev.kotlinautas.twitch4k.util.MessageType
import java.util.Date

data class ClearChatIMessage(
    val raw: String,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String,
    val channel: String,
    val roomId: String,
    val time: Date,
    val banDuration: Int,
    val targetUserId: String,
    val targetUserName: String,
    override val type: MessageType,
) : IMessage
