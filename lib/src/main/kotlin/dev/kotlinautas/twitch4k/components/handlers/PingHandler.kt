package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender

class PingHandler(
    private val sender: Sender,
) : AbstractMessageHandler() {

    override val codes: Array<String> = arrayOf("PING")

    override fun handle(rawMessage: RawMessage) {
        logger.info("Mensagem de PING recebida, respondendo com um PONG")
        sender.sendMessage(TwitchMessages.pongMessage(rawMessage.params.first()))
    }
}