package dev.kotlinautas.twitch4k.mock

import dev.kotlinautas.twitch4k.entity.message.PingMessage
import dev.kotlinautas.twitch4k.util.MessageType

object RawMessageConverterMock {

    fun getPingRawMessage() = "PING :tmi.twitch.tv"
    fun getPingMessage() = PingMessage(
        raw = getPingRawMessage(),
        type = MessageType.PING,
        param = ":tmi.twitch.tv"
    )

}