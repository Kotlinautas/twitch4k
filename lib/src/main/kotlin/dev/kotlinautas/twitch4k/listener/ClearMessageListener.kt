package dev.kotlinautas.twitch4k.listener

interface ClearMessageListener {

    fun onClearMessageListener(channel: String, username: String, deletedMessage: String)

}