package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil

class JoinHandler : AbstractMessageHandler() {

    override fun handle(message: RawMessage, sender: Sender) {
        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(message.prefix)
        logger.info("$chatter entrou no canal ${message.params.first()}")
    }
}