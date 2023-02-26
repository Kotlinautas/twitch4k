package dev.kotlinautas.twitch4k.entities

data class Reply (
    val parentMessageId: String,
    val parentUserId: String,
    val parentUserLogin: String,
    val parentDisplayName: String,
    val parentMessageBody: String
)
