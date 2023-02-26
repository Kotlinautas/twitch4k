package dev.kotlinautas.twitch4k.util

import dev.kotlinautas.twitch4k.entities.Emote
import dev.kotlinautas.twitch4k.entities.Position
import dev.kotlinautas.twitch4k.entities.Reply
import dev.kotlinautas.twitch4k.entities.User
import java.time.Instant
import java.util.*

fun Map<String, String>.getMessageId() = this["id"] ?: ""

fun Map<String, String>.getDate() =
    this["tmi-sent-tsd"]?.toLongOrNull()?.let { date -> Date(Instant.ofEpochMilli(date).epochSecond) } ?: Date()

fun Map<String, String>.getBadges() = this["badges"] ?: ""
fun Map<String, String>.getColor() = this["color"] ?: ""

fun Map<String, String>.getUserId() = this["user-id"]?.toLong() ?: 0

fun Map<String, String>.getRoomId() = this["room-id"] ?: ""

fun Map<String, String>.isSubscriber() = this["subscriber"]?.toInt() == 1

fun Map<String, String>.isModerator() = this["mod"]?.toInt() == 1

fun Map<String, String>.isFirstMessage() = this["first-msg"]?.toInt() == 1

fun Map<String, String>.getDisplayName() = this["display-name"] ?: ""

fun Map<String, String>.getEmotes(): List<Emote> {
    val emotesList = mutableListOf<Emote>()

    val emotesString = this["emotes"] ?: ""
    if (emotesString.isNotEmpty()) {
        emotesString
            .split("/")
            .forEach { emote ->
                val positions = mutableListOf<Position>()
                val aux = emote.split(":", limit = 2)
                val pairs = aux.last().split(",")
                pairs.forEach {
                    val pos = it.split("-")
                    val start = pos.first().toInt()
                    val end = pos.last().toInt()
                    positions.add(Position(start, end))
                }

                emotesList.add(
                    Emote(
                        name = emotesString.substring(positions.first().start, positions.first().end),
                        id = aux.first(),
                        count = pairs.size,
                        positions = positions
                    )
                )
            }
    }
    return emotesList
}

fun Map<String, String>.getBits() = this["bits"]?.toInt() ?: 0

fun Map<String, String>.getReply(): Reply? {
    var reply: Reply? = null
    val replyString = this["reply-parent-msg-id"] ?: ""
    if (replyString.isNotEmpty()) {
        reply = Reply(
            parentMessageId = this["reply-parent-msg-id"] ?: "",
            parentUserId = this["reply-parent-user-id"] ?: "",
            parentUserLogin = this["reply-parent-user-login"] ?: "",
            parentDisplayName = this["reply-parent-display-name"] ?: "",
            parentMessageBody = this["reply-parent-msg-body"] ?: "",
        )
    }
    return reply
}