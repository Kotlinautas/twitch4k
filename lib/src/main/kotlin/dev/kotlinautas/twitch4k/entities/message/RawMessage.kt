package dev.kotlinautas.twitch4k.entities.message

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.util.MessageType
import java.util.*

data class RawMessage(
    val raw: String,
    val type: MessageType,
    val rawType: String,
    val tags: Map<String, String>,
    val message: String
)
