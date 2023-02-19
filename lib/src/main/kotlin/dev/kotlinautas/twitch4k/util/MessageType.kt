package dev.kotlinautas.twitch4k.util

enum class MessageType {
    AUTH_SUCCESS,
    UNKNOW,
    WHISPER,
    PRIVATE_MESSAGE,
    CLEAR_CHAT,
    ROOM_STATE,
    USER_NOTICE,
    USER_STATE,
    NOTICE,
    JOIN,
    PART,
    RECONNECT,
    NAMES,
    PING,
    PONG,
    CLEAR_MESSAGE,
    GLOBAL_USER_STATE
}