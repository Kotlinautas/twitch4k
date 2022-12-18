package dev.kotlinautas.twitch4k.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class User(
    val id: Long,
    val displayName: String,
    val isSubscriber: Boolean,
    val isModerator: Boolean,
    val isFirstMessage: Boolean,
) {

    fun toJson():String = Json.encodeToString(this)

}
