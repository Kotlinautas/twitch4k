package dev.kotlinautas.twitch4k.interfaces.event

import dev.kotlinautas.twitch4k.entities.ChannelStatus
import dev.kotlinautas.twitch4k.interfaces.bus.IEvent

class OnConnectedEvent(
    private val channel: String,
    private val isConnected: Boolean
) : IEvent<ChannelStatus> {
    override fun getData() = ChannelStatus(channel, isConnected)

}