package dev.kotlinautas.twitch4k.entity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

data class ChatMessage(
    val id: String,
    val date: Date,
    val channel: String,
    val text: String,
    val user: User,
) {
    private val mapper = jacksonObjectMapper()
    fun toJson():String = mapper.writeValueAsString(this)
}
