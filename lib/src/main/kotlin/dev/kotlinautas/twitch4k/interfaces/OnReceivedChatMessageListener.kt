package dev.kotlinautas.twitch4k.interfaces

import dev.kotlinautas.twitch4k.entity.Chat
import dev.kotlinautas.twitch4k.entity.ChatMessage

interface OnReceivedChatMessageListener : EventListener {

    fun onReceived(message: ChatMessage, chat: Chat)

}