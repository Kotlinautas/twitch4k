package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.components.handlers.AuthenticationHandler
import dev.kotlinautas.twitch4k.components.handlers.MessageHandlerDiscovery
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.io.InputStream

class TwitchHandler(
    private val inputStream: InputStream,
    val channels: List<String>,
    val sender: Sender
) : Runnable {

    private val logger = LoggerFactory.getLogger("TWITCH_HANDLER")

    var onReceivedChatMessageListener: OnReceivedChatMessageListener? = null

    override fun run() = runBlocking {
        val messageHandlerDiscovery = MessageHandlerDiscovery()
        messageHandlerDiscovery.onReceivedChatMessageListener = onReceivedChatMessageListener

        val bufferedReader = inputStream.bufferedReader()
        while (true) {
            val message = bufferedReader.readLine()
            if (message != null) {
                launch(Dispatchers.Main, CoroutineStart.UNDISPATCHED) {
                    val rawMessage = IRCMessageUtil.parseRawMessage(message)
                    val handler = if (rawMessage.command == "001")
                        AuthenticationHandler(channels)
                    else messageHandlerDiscovery.handleMessageFor(rawMessage)
                    handler?.handle(rawMessage, sender) ?: logger.error("Comando não esperado: ${rawMessage.command}")
                }
            }
        }
    }
}