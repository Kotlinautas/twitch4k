package dev.kotlinautas.twitch4k.interfaces.listener

import dev.kotlinautas.twitch4k.entities.ChannelStatus

interface OnChannelJoinedListener {

    fun onConnected(status: ChannelStatus, say: (channel: String, text: String) -> Unit)

}