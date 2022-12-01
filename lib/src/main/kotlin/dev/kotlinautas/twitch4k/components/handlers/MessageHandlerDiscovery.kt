package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener

class MessageHandlerDiscovery() {

    var onReceivedChatMessageListener: OnReceivedChatMessageListener? = null

    fun handleMessageFor(message: RawMessage): MessageHandler? {
        return when (message.command) {
            "CAP" -> CapabilityHandler()
            "JOIN" -> JoinHandler()
            "NOTICE" -> NoticeHandler()
            "PART" -> PartHandler()
            "PING" -> PingHandler()
            "PRIVMSG" -> PrivateMessageHandler(onReceivedChatMessageListener)
            in arrayOf("USERSTATE", "ROOMSTATE") -> StateHandler()
            in arrayOf(
                "002",
                "003",
                "004",
                "353",
                "366",
                "372",
                "375",
                "376"
            ) -> NotImplementedHandler()

            else -> null
        }
    }
}