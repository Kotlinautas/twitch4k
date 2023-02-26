package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.interfaces.event.OnChannelJoinEvent
import dev.kotlinautas.twitch4k.AsyncEventBus
import dev.kotlinautas.twitch4k.entities.message.IrcMessage
import dev.kotlinautas.twitch4k.entities.message.PingMessage
import dev.kotlinautas.twitch4k.entities.message.PrivateMessage
import dev.kotlinautas.twitch4k.interfaces.event.OnPrivateMessageEvent
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
                val ircMessage = IRCMessageUtil.parseIrcMessage(message)

                when (ircMessage.getMessageType()) {
                    MessageType.AUTH_SUCCESS -> connectedAction()
                    MessageType.PRIVATE_MESSAGE -> {
                        val privateMessage = ircMessage.toPrivateMessage()
                        notifyPrivateMessage(privateMessage)
                    }
                    MessageType.PING -> {
                        val pingMessage = ircMessage.toPingMessage()
                        sendPongMessage(pingMessage)
                    }
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
                        //logger.info("Mensagem desconhecida: $rawMessage")
                    }
                }
            }
        }
    }

    private fun sendPongMessage(pingMessage: PingMessage) {
        logger.info("Mensagem de PING recebida, respondendo com um PONG")
        socket.getOutputStream()
            .write(TwitchMessages.pongMessage(pingMessage.message).toByteArray())
    }

    private fun notifyPrivateMessage(privateMessage: PrivateMessage) {
        AsyncEventBus.dispatch(OnPrivateMessageEvent(privateMessage))
    }

    private fun connectedAction() {
        logger.info("Conectado ao Servidor IRC da Twitch")
        joinChannels()
    }

    private fun joinChannels() {
        channels.forEach { channel ->
            AsyncEventBus.dispatch(OnChannelJoinEvent(channel, isConnected = true))
            socket
                .getOutputStream()
                .write(TwitchMessages.joinMessage(channel).toByteArray())
        }
    }

}