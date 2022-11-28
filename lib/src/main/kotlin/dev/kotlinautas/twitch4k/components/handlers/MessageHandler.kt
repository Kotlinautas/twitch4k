package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender

interface MessageHandler {

    fun handle(message: RawMessage, sender: Sender)
}