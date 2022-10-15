package dev.kotlinautas.twitch4k.listener

interface JoinChannelListener {
    fun onJoinChannelListener(channel:String, nick:String)
}