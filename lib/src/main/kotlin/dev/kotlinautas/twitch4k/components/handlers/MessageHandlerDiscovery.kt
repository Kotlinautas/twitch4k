package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage

class MessageHandlerDiscovery() {

    fun handleMessageFor(message: RawMessage): MessageHandler? {
        return when (message.command) {
            in arrayOf("CAP") -> CapabilityHandler()
            in arrayOf("JOIN") -> JoinHandler()
            in arrayOf("NOTICE") -> NoticeHandler()
            in arrayOf("PART") -> PartHandler()
            in arrayOf("PING") -> PingHandler()
            in arrayOf("PRIVMSG") -> PrivateMessageHandler()
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