package dev.kotlinautas.twitch4k.entity

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

data class ChatMessage(
    val id: String,
    val date: Date,
    val channel: String,
    val text: String,
    val user: User,
) {
    fun toJson():String = Json.encodeToString(this)
}
