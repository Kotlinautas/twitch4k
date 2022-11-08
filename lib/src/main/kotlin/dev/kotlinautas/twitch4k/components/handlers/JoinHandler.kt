package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.util.IRCMessageUtil

class JoinHandler : AbstractMessageHandler() {
    override val codes: Array<String> = arrayOf("JOIN")

    override fun handle(rawMessage: RawMessage) {
        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
        logger.info("$chatter entrou no canal ${rawMessage.params.first()}")
    }
}