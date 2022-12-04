package dev.kotlinautas.twitch4k.interfaces

import dev.kotlinautas.twitch4k.entity.Chat

fun interface OnConnectedListener {

    fun onConnected(channels: List<String>, chat: Chat)

}