package dev.kotlinautas.twitch4k.listener

interface UserStateListener {
    fun onUserStateListener(usename: String, tags: Map<String, String>)
}