package dev.kotlinautas.twitch4k.interfaces.listener

import dev.kotlinautas.twitch4k.entities.message.PrivateMessage

interface OnPrivateMessageListener {

    fun onPrivateMessage(message: PrivateMessage, say: (channel: String, text: String) -> Unit)

}
