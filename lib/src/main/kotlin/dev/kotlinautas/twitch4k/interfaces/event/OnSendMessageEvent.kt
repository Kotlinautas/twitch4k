package dev.kotlinautas.twitch4k.interfaces.event

import dev.kotlinautas.twitch4k.interfaces.bus.IEvent

class OnSendMessageEvent(private val message: String) : IEvent<String> {
    override fun getData(): String = message
}