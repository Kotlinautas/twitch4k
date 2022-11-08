package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage

class NotImplementedHandler : AbstractMessageHandler() {
    override val codes: Array<String> = arrayOf(
        "002",
        "003",
        "004",
        "353",
        "366",
        "372",
        "375",
        "376"
    )

    override fun handle(message: RawMessage) { }
}