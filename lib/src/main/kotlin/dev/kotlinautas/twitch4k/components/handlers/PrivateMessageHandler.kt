package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil

class PrivateMessageHandler(private val listener: OnReceivedChatMessageListener?) : AbstractMessageHandler() {

    override fun handle(rawMessage: RawMessage, sender: Sender) {
        logger.info(rawMessage.toString())
        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
        val channel = rawMessage.params.first().removePrefix("#")
        val text = rawMessage.params.last().removePrefix(":")
        logger.info("[$channel] $chatter: $text")

        listener?.onReceived(rawMessage.toChatMessage(), sender)
    }

}