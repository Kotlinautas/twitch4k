package dev.kotlinautas.twitch4k.entities.message

import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.util.*

class IrcMessage private constructor(
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
        fun create(operations: Builder.() -> Unit): IrcMessage {
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

        fun build() = IrcMessage(
            raw = this.raw ?: throw NullPointerException("A propriedade raw não pode ser nula"),
            command = this.command ?: throw NullPointerException("A propriedade command não pode ser nula"),
            tags = this.tags.toMap(),
            prefix = this.prefix,
            params = this.params.toList()
        )
    }

    fun toRawMessage(): RawMessage = RawMessage(
        raw,
        getMessageType(command),
        command,
        tags,
        params.joinToString()
    )

    private fun getMessageType(command: String): MessageType {
        return when (command) {
            "001" -> MessageType.AUTH_SUCCESS
            "353" -> MessageType.NAMES
            "WHISPER" -> MessageType.WHISPER
            "PRIVMSG" -> MessageType.PRIVATE_MESSAGE
            "CLEARCHAT" -> MessageType.CLEAR_CHAT
            "ROOMSTATE" -> MessageType.ROOM_STATE
            "USERNOTICE" -> MessageType.USER_NOTICE
            "USERSTATE" -> MessageType.USER_STATE
            "NOTICE" -> MessageType.NOTICE
            "JOIN" -> MessageType.JOIN
            "PART" -> MessageType.PART
            "RECONNECT" -> MessageType.RECONNECT
            "PING" -> MessageType.PING
            "PONG" -> MessageType.PONG
            "CLEARMSG" -> MessageType.CLEAR_MESSAGE
            "GLOBALUSERSTATE" -> MessageType.GLOBAL_USER_STATE
            else -> MessageType.UNKNOW
        }
    }
}