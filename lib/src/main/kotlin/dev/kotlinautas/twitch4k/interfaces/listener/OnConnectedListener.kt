package dev.kotlinautas.twitch4k.interfaces.listener

import dev.kotlinautas.twitch4k.entities.ChannelStatus

interface OnConnectedListener {

    fun onConnected(status: ChannelStatus, send: (String) -> Unit)

}