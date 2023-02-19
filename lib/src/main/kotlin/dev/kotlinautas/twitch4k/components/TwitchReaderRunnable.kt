package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.interfaces.event.OnConnectedEvent
import dev.kotlinautas.twitch4k.AsyncEventBus
import dev.kotlinautas.twitch4k.entities.message.RawMessage
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import dev.kotlinautas.twitch4k.util.MessageType
import org.slf4j.LoggerFactory
import java.net.Socket

class TwitchReaderRunnable(private val socket: Socket, private val channels: List<String>) : Runnable {

    private val logger = LoggerFactory.getLogger("[TWITCH4K-READER]")

    override fun run() {

        val reader = socket.getInputStream().bufferedReader()

        while (true) {
            val message = reader.readLine()
            if (message != null) {

                logger.info(message)
                val rawMessage = IRCMessageUtil.parseIrcMessage(message).toRawMessage()

                when (rawMessage.type) {
                    MessageType.AUTH_SUCCESS -> connectedAction()
                    MessageType.PRIVATE_MESSAGE -> notifyPrivateMessage()
                    MessageType.PING -> sendPongMessage(rawMessage)
                    MessageType.WHISPER -> {}
                    MessageType.CLEAR_CHAT -> {}
                    MessageType.ROOM_STATE -> {}
                    MessageType.USER_NOTICE -> {}
                    MessageType.USER_STATE -> {}
                    MessageType.NOTICE -> {}
                    MessageType.JOIN -> {}
                    MessageType.PART -> {}
                    MessageType.RECONNECT -> {}
                    MessageType.NAMES -> {}
                    MessageType.PONG -> {}
                    MessageType.CLEAR_MESSAGE -> {}
                    MessageType.GLOBAL_USER_STATE -> {}

                    MessageType.UNKNOW -> {
                        logger.info("Mensagem desconhecida: $rawMessage")
                    }
                }
            }
        }
    }

    private fun sendPongMessage(rawMessage: RawMessage) {
        logger.info("Mensagem de PING recebida, respondendo com um PONG")
        socket.getOutputStream().write(TwitchMessages.pongMessage(rawMessage.message).toByteArray())
    }

    private fun notifyPrivateMessage() {

    }

    private fun connectedAction() {
        logger.info("Conectado ao Servidor IRC da Twitch")
        joinChannels()
    }

    private fun joinChannels() {
        channels.forEach { channel ->
            AsyncEventBus.dispatch(OnConnectedEvent(channel, isConnected = true))
            socket
                .getOutputStream()
                .write(TwitchMessages.joinMessage(channel).toByteArray())
        }
    }

}