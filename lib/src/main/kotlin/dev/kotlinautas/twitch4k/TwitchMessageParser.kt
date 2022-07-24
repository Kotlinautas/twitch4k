package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.entity.RawMessage

object IRCMessageUtil {

    fun parseRawMessage(message: String): RawMessage {
        val builder = RawMessage.Builder()
        builder.setRaw(message)
        message
            .let { extractTags(it, builder) }
            .let { extractPrefix(it, builder) }
            .let { extractCommand(it, builder) }
            .let { extractParams(it, builder) }
        return builder.build()
    }

    private fun extractParams(message: String, builder: RawMessage.Builder) {
        var remaining = message
        while (remaining.isNotEmpty()) {
            remaining = when {
                remaining.startsWith(":") -> {
                    builder.addParam(remaining)
                    ""
                }
                else -> {
                    val aux = remaining.split(" ", limit = 2)
                    builder.addParam(aux.first())
                    if (aux.size == 2) aux.last() else ""
                }
            }
        }
    }

    private fun extractCommand(message: String, builder: RawMessage.Builder): String {
        val aux = message.split(" ", limit = 2)
        builder.setCommand(aux.first())
        return aux.last()
    }

    private fun extractPrefix(message: String, builder: RawMessage.Builder): String {
        if (message.startsWith(":")) {
            val aux = message.split(" ", limit = 2)
            builder.setPrefix(aux.first())
            return aux.last()
        }
        return message
    }

    private fun extractTags(message: String, builder: RawMessage.Builder): String {
        if (message.startsWith("@")) {
            val aux = message.substring(1).split(" ", limit = 2)
            aux.first().split(";").forEach {
                val tag = it.split("=")
                builder.addTag(tag.first(), tag.last())
            }
            return aux.last()
        }
        return message
    }

}