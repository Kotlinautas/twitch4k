package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.util.IRCMessageUtil

class PrivateMessageHandler : AbstractMessageHandler() {
    override val codes: Array<String> = arrayOf("PRIVMSG")

    override fun handle(rawMessage: RawMessage) {
        logger.info(rawMessage.toString())
        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
        val channel = rawMessage.params.first().removePrefix("#")
        val text = rawMessage.params.last().removePrefix(":")
        logger.info("[$channel] $chatter: $text")
        //TODO: Implementar um Observer aqui
    }
}