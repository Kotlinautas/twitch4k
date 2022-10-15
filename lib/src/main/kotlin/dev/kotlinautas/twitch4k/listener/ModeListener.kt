package dev.kotlinautas.twitch4k.listener

interface ModeListener {
    fun onModListener(channel:String, username:String)
    fun onUnmodListener(channel:String, username:String)
}