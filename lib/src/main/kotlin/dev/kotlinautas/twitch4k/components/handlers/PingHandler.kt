package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender

class PingHandler() : AbstractMessageHandler() {

    override fun handle(rawMessage: RawMessage, sender: Sender) {
        logger.info("Mensagem de PING recebida, respondendo com um PONG")
        sender.sendMessage(TwitchMessages.pongMessage(rawMessage.params.first()))
    }
}