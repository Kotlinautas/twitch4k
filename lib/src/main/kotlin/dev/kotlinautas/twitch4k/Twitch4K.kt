package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.components.TwitchHandler
import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.components.TwitchSender
import dev.kotlinautas.twitch4k.entity.ChatMessage
import dev.kotlinautas.twitch4k.interfaces.OnReceivedChatMessageListener
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

private const val TWITCH_IRC_SERVER = "irc.chat.twitch.tv"
private const val TWITCH_IRC_PORT = 6667
private const val TWITCH_IRC_SSL_PORT = 6697

class Twitch4K constructor(
    val username: String,
    val token: String,
    val channels: List<String>
) : Sender {

    private val logger = LoggerFactory.getLogger("TWITCH4K")
    private val queue: Queue<String> = ConcurrentLinkedQueue()

    private var onReceivedChatMessageListener: OnReceivedChatMessageListener? = null

    fun setOnReceivedChatMessageListener(listener: OnReceivedChatMessageListener){
        this.onReceivedChatMessageListener = listener
    }

    fun start() {

        val socket = Socket()
        socket.receiveBufferSize = 4096
        socket.sendBufferSize = 4096
        socket.reuseAddress = true
        socket.tcpNoDelay = true

        logger.info("Conectando ao host $TWITCH_IRC_SERVER")
        socket.connect(InetSocketAddress(TWITCH_IRC_SERVER, TWITCH_IRC_PORT))

        if (socket.isConnected) {

            // Recuperando os fluxos de entrada e saĩda de dados do socket
            val inputStream = socket.getInputStream()
            val outputStream = socket.getOutputStream()

            val twitchHandler = TwitchHandler(inputStream, channels, this)
            twitchHandler.onReceivedChatMessageListener = onReceivedChatMessageListener


            // Criando a thread responsável por manipular as mensagens da Twitch
            val twitchHandlerThread = Thread(twitchHandler)
            twitchHandlerThread.start()

            // Criando a thread responsável por enviar as mensagens para Twitch
            val twitchSenderThread = Thread(TwitchSender(outputStream, queue))
            twitchSenderThread.start()

            // Envia o nome de utilizador e a senha
            sendMessage(TwitchMessages.passMessage(token))
            sendMessage(TwitchMessages.nickMessage(username))

            twitchHandlerThread.join()
            twitchSenderThread.join()

        }
    }
    override fun sendMessage(message: String) {
        queue.add(message)
    }

}