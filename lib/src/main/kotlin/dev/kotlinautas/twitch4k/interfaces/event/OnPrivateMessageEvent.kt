package dev.kotlinautas.twitch4k.interfaces.event

import dev.kotlinautas.twitch4k.entities.message.PrivateMessage
import dev.kotlinautas.twitch4k.interfaces.bus.IEvent

class OnPrivateMessageEvent(
    private val privateMessage: PrivateMessage,
): IEvent<PrivateMessage> {
    override fun getData(): PrivateMessage = privateMessage
}