package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.interfaces.Sender

class MessageHandlerDiscovery(
    channels: List<String>,
    sender: Sender
) {

    private val handlers = listOf(
        AuthenticationHandler(channels, sender),
        CapabilityHandler(),
        JoinHandler(),
        NoticeHandler(),
        NotImplementedHandler(),
        PartHandler(),
        PingHandler(sender),
        PrivateMessageHandler(),
        StateHandler()
    )

    fun handleMessageFor(code: String) = handlers.find { it.codes.contains(code) }
}