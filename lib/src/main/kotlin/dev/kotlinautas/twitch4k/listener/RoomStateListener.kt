package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.types.RoomStateType

interface RoomStateListener {
    fun onRoomState(type: RoomStateType, channel: String, tags: Map<String, String>)
}