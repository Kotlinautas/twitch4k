package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.components.handlers.MessageHandlerDiscovery
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import org.slf4j.LoggerFactory
import java.io.InputStream
import java.security.Key

class TwitchHandler(
    private val inputStream: InputStream,
    channels: List<String>,
    sender: Sender
) : Runnable {

    private val logger = LoggerFactory.getLogger("TWITCH_HANDLER")
    private val messageHandlerDiscovery = MessageHandlerDiscovery(channels, sender)

    override fun run() {
        val bufferedReader = inputStream.bufferedReader()
        while (true) {
            val message = bufferedReader.readLine()
            if (message != null) {
                val rawMessage = IRCMessageUtil.parseRawMessage(message)
                val handler = messageHandlerDiscovery.handleMessageFor(rawMessage.command)
                handler?.handle(rawMessage) ?: logger.error("Comando não esperado: ${rawMessage.command}")
            }
        }
    }
}