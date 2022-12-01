package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.components.TwitchHandler
import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.components.TwitchSender
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener
import dev.kotlinautas.twitch4k.interfaces.Sender
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

private const val TWITCH_IRC_SERVER = "irc.chat.twitch.tv"
private const val TWITCH_IRC_PORT = 6667
private const val TWITCH_IRC_SSL_PORT = 6697

class Twitch4K constructor(
    private val username: String,
    private val token: String,
    private val channels: List<String>
) : Sender {

    private val logger = LoggerFactory.getLogger("TWITCH4K")
    private val queue: Queue<String> = ConcurrentLinkedQueue()

    private var onReceivedChatMessageListener: OnReceivedChatMessageListener? = null

    fun setOnReceivedChatMessageListener(listener: OnReceivedChatMessageListener) {
        this.onReceivedChatMessageListener = listener
    }

    fun start() {
        val socket = createSocket()

        if (socket.isConnected) {
            val twitchHandlerThread = createAndStartTwitchHandlerThread(socket)
            val twitchSenderThread = createAndStartTwitchSenderThread(socket)

            sendMessage(TwitchMessages.passMessage(token))
            sendMessage(TwitchMessages.nickMessage(username))

            twitchHandlerThread.join()
            twitchSenderThread.join()
        }
    }

    override fun sendMessage(message: String) {
        queue.add(message)
    }

    private fun createSocket(): Socket {
        val socket = Socket().apply {
            receiveBufferSize = 4096
            sendBufferSize = 4096
            reuseAddress = true
            tcpNoDelay = true
        }

        return socket.also {
            logger.info("Conectando ao host $TWITCH_IRC_SERVER")
            it.connect(InetSocketAddress(TWITCH_IRC_SERVER, TWITCH_IRC_PORT))
        }
    }

    private fun createAndStartTwitchHandlerThread(socket: Socket): Thread {
        val twitchHandler = TwitchHandler(socket.getInputStream(), channels, this).also { handler ->
            handler.onReceivedChatMessageListener = onReceivedChatMessageListener
        }
        return Thread(twitchHandler).also { it.start() }
    }

    private fun createAndStartTwitchSenderThread(socket: Socket): Thread {
        return Thread(TwitchSender(socket.getOutputStream(), queue)).also { it.start() }
    }

}