package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage

interface MessageHandler {
    val codes: Array<String>
    fun handle(rawMessage: RawMessage)
}