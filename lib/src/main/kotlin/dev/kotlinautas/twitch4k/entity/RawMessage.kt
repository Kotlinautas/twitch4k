package dev.kotlinautas.twitch4k.entity

import dev.kotlinautas.twitch4k.util.*
import java.sql.Timestamp
import java.time.Instant
import java.util.*


class RawMessage private constructor(
    val raw: String,
    val tags: Map<String, String>,
    val prefix: String,
    val command: String,
    val params: List<String>
) {

    override fun toString(): String {
        return "[tags=$tags, prefix=$prefix, command=$command, params=$params]"
    }

    companion object {
        @JvmStatic
        fun create(operations: Builder.() -> Unit): RawMessage {
            val builder = Builder()
            builder.operations()
            return builder.build()
        }

    }

    class Builder {

        var raw: String? = null
        var tags: MutableMap<String, String> = mutableMapOf()
        var prefix: String = ""
        var command: String? = null
        val params: MutableList<String> = mutableListOf()

        fun build() = RawMessage(
            raw = this.raw ?: throw NullPointerException("A propriedade raw não pode ser nula"),
            command = this.command ?: throw NullPointerException("A propriedade command não pode ser nula"),
            tags = this.tags.toMap(),
            prefix = this.prefix,
            params = this.params.toList()
        )
    }

    fun toChatMessage(): ChatMessage = ChatMessage(
        id = tags.getMessageId(),
        date = tags.getDate(),
        channel = params.first().substringAfter("#"),
        text = params.last().substringAfter(":"),
        user = User(
            id = tags.getUserId(),
            displayName = tags.getDisplayName(),
            isSubscriber = tags.isSubscriber(),
            isModerator = tags.isModerator(),
            isFirstMessage = tags.isFirstMessage(),
        )
    )
}