package dev.kotlinautas.twitch4k.util

import dev.kotlinautas.twitch4k.entity.message.RawMessage


private const val AT_SYMBOL = "@"
private const val EQUAL_SYMBOL = "="
private const val COLON_SYMBOL = ":"
private const val EXCLAMATION_SYMBOL = "!"
private const val BLANK_SPACE_SYMBOL = " "
private const val EMPTY_SYMBOL = ""

object TwitchMessageUtil {

    fun parseTags(tags: String): Map<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()
        if (tags.isNotEmpty()) {
            tags
                .substringAfter(AT_SYMBOL)
                .split(";")
                .forEach {
                    val aux = it.split(EQUAL_SYMBOL)
                    map[aux.first()] = aux.last()
                }
        }
        return map
    }

    fun parseParams(params: String): List<String> {
        val list = mutableListOf<String>()
        params
            .split(BLANK_SPACE_SYMBOL)
            .forEach { value -> list.add(value) }
        return list
    }

    fun parse(message: String): RawMessage {
        when {
            message.startsWith(AT_SYMBOL) -> {
                val aux = message.split(BLANK_SPACE_SYMBOL, limit = 4)
                return RawMessage(
                    raw = message,
                    tags = parseTags(aux[0]),
                    prefix = aux[1],
                    command = aux[2],
                    params = parseParams(aux[3])
                )
            }

            message.startsWith(COLON_SYMBOL) -> {
                val aux = message.split(BLANK_SPACE_SYMBOL, limit = 3)
                return RawMessage(
                    raw = message,
                    tags = parseTags(EMPTY_SYMBOL),
                    prefix = aux[0],
                    command = aux[1],
                    params = parseParams(aux[2])
                )
            }

            else -> {
                val aux = message.split(BLANK_SPACE_SYMBOL, limit = 2)
                return RawMessage(
                    raw = message,
                    tags = parseTags(EMPTY_SYMBOL),
                    prefix = EMPTY_SYMBOL,
                    command = aux[0],
                    params = parseParams(aux[1])
                )
            }
        }

    }

}