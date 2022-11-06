package dev.kotlinautas.twitch4k.util

import dev.kotlinautas.twitch4k.entity.RawMessage

object IRCMessageUtil {

    fun parseRawMessage(message: String): RawMessage {
        return RawMessage.create {
            raw = message
            message
                .extractTags(this)
                .extractPrefix(this)
                .extractCommand(this)
                .extractParams(this)
        }
    }

    fun getChatterUsernameFromPrefix(prefix: String): String {
        return prefix
            .removePrefix(":")
            .substringBefore("!")
    }

    private fun String.extractParams(builder: RawMessage.Builder): String {
        var remaining = this
        while (remaining.isNotEmpty()) {
            remaining = when {
                remaining.startsWith(":") -> {
                    builder.params.add(remaining)
                    ""
                }

                else -> {
                    val aux = remaining.split(" ", limit = 2)
                    builder.params.add(aux.first())
                    if (aux.size == 2) aux.last() else ""
                }
            }
        }
        return ""
    }

    private fun String.extractCommand(builder: RawMessage.Builder): String {
        val aux = this.split(" ", limit = 2)
        builder.command = aux.first()
        return aux.last()
    }

    private fun String.extractPrefix(builder: RawMessage.Builder): String {
        if (this.startsWith(":")) {
            val aux = this.split(" ", limit = 2)
            builder.prefix = aux.first()
            return aux.last()
        }
        return this
    }

    private fun String.extractTags(builder: RawMessage.Builder): String {
        if (this.startsWith("@")) {
            val aux = this.substring(1).split(" ", limit = 2)
            aux.first().split(";").forEach {
                val tag = it.split("=")
                builder.tags[tag.first()] = tag.last()
            }
            return aux.last()
        }
        return this
    }

}