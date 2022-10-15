package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.types.ClearChatType

interface ClearChatListener {

    fun onClearChatListener(type: ClearChatType, channel: String, msg: String)

}