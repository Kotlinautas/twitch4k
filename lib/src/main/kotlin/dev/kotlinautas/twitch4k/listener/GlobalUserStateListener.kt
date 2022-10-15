package dev.kotlinautas.twitch4k.listener

interface GlobalUserStateListener {
    fun onGlobalUserState(tags: Map<String, String>?)
}