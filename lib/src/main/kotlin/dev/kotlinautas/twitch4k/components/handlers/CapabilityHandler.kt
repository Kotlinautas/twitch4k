package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage

class CapabilityHandler : AbstractMessageHandler() {

    override val codes: Array<String> = arrayOf("CAP")

    override fun handle(rawMessage: RawMessage) {
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