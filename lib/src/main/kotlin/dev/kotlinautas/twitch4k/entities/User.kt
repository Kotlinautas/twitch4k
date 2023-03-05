package dev.kotlinautas.twitch4k.entities

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

data class User(
    val id: Long,
    val displayName: String,
    val color: String,
    val badges: Map<String, Int>,
    val isSubscriber: Boolean,
    val isModerator: Boolean,
    val isFirstMessage: Boolean,
) {

    val isBroadcaster get() = this.badges.containsKey("broadcaster")

    private val mapper = jacksonObjectMapper()
    fun toJson():String = mapper.writeValueAsString(this)
}

