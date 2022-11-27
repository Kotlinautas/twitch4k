package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender
import java.nio.channels.Channels

class AuthenticationHandler(private val channels: List<String>) : AbstractMessageHandler() {

    override fun handle(message: RawMessage, sender: Sender) {
        logger.info("Autenticado com sucesso!")

        // Registrando nas Twitch Capabilities
        sender.sendMessage(TwitchMessages.membershipCommands())
        sender.sendMessage(TwitchMessages.membershipCapability())
        sender.sendMessage(TwitchMessages.membershipTags())

        // Entrando nos canais
        channels.forEach { channel ->
            sender.sendMessage(TwitchMessages.joinMessage(channel))
        }
    }
}