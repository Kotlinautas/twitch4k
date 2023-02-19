package dev.kotlinautas.twitch4k.entities

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class User(
    val id: Long,
    val displayName: String,
    val isSubscriber: Boolean,
    val isModerator: Boolean,
    val isFirstMessage: Boolean,
) {
    private val mapper = jacksonObjectMapper()
    fun toJson():String = mapper.writeValueAsString(this)
}

