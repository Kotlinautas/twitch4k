package dev.kotlinautas.twitch4k.interfaces

import dev.kotlinautas.twitch4k.util.MessageType

interface IMessage {

    val type: MessageType
    fun getType(): MessageType = type
}