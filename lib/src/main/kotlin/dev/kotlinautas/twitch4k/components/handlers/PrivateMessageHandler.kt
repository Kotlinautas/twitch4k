package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.Chat
import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil

class PrivateMessageHandler(private val listener: OnReceivedChatMessageListener?) : AbstractMessageHandler() {

    override fun handle(message: RawMessage, sender: Sender) {
        logger.info(message.toString())
        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(message.prefix)
        val channel = message.params.first().removePrefix("#")
        val text = message.params.last().removePrefix(":")
        logger.info("[$channel] $chatter: $text")

        val receivedMessage = message.toChatMessage()
        val chat = Chat(sender)
        listener?.onReceived(receivedMessage, chat)
    }

}