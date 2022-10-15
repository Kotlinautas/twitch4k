package dev.kotlinautas.twitch4k

interface Dispatcher {
    suspend fun sendMessage(message: String)
}