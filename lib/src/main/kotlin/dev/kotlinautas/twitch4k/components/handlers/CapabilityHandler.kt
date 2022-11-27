package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender

class CapabilityHandler : AbstractMessageHandler() {

    override fun handle(rawMessage: RawMessage, sender: Sender) {
        if (rawMessage.params.contains("ACK")) {
            val command = rawMessage.params.last()
            when {
                command.contains("commands") -> logger.info("Recebido o ACK da Capability COMMANDS")
                command.contains("membership") -> logger.info("Recebido o ACK da Capability MEMBERSHIP")
                command.contains("tags") -> logger.info("Recebido o ACK da Capability TAGS")
            }
        }
    }
}