package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.entity.RawMessage
import dev.kotlinautas.twitch4k.Dispatcher

interface PrivateMessageListener {
    fun onPrivateMessageListener(username: String, message: String, rawMessage: RawMessage, dispatcher: Dispatcher)
}