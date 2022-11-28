package dev.kotlinautas.twitch4k.util

import java.time.Instant
import java.util.*

fun Map<String, String>.getMessageId() = this["id"] ?: ""

fun Map<String, String>.getDate() =
    this["tmi-sent-tsd"]?.toLongOrNull()?.let { date -> Date(Instant.ofEpochMilli(date).epochSecond) } ?: Date()

fun Map<String, String>.getUserId() = this["user-id"]?.toLong() ?: 0
fun Map<String, String>.isSubscriber() = this["subscriber"] ?: 0 == 0

fun Map<String, String>.isModerator() = this["mod"] ?: 0 == 0

fun Map<String, String>.isFirstMessage() = this["first-msg"] ?: 0 == 0

fun Map<String, String>.getDisplayName() = this["display-name"] ?: ""