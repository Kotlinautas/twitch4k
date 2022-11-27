package dev.kotlinautas.twitch4k.interfaces

import dev.kotlinautas.twitch4k.entity.ChatMessage

interface OnReceivedMessageEventListener {

    fun onReceived(message: ChatMessage, sender: Sender)

}