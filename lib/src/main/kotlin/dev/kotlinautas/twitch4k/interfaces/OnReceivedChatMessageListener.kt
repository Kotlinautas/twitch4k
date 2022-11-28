package dev.kotlinautas.twitch4k.interfaces

import dev.kotlinautas.twitch4k.entity.ChatMessage

interface OnReceivedChatMessageListener : EventListener {

    fun onReceived(message: ChatMessage, sender: Sender)

}