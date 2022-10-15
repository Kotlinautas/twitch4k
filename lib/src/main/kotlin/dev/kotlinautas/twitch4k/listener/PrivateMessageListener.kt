package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.Dispatcher
import dev.kotlinautas.twitch4k.entity.RawMessage

interface PrivateMessageListener {
    fun onPrivateMessageListener(username: String, message: String, rawMessage: RawMessage, dispatcher: Dispatcher)
}