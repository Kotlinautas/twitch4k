package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.interfaces.bus.IEvent
import dev.kotlinautas.twitch4k.interfaces.bus.ISubscribable
import dev.kotlinautas.twitch4k.interfaces.event.OnChannelJoinEvent
import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.components.TwitchReaderRunnable
import dev.kotlinautas.twitch4k.components.TwitchWriterRunnable
import dev.kotlinautas.twitch4k.entities.ChannelStatus
import dev.kotlinautas.twitch4k.entities.message.PrivateMessage
import dev.kotlinautas.twitch4k.interfaces.event.OnPrivateMessageEvent
import dev.kotlinautas.twitch4k.interfaces.event.OnSendMessageEvent
import dev.kotlinautas.twitch4k.interfaces.listener.OnChannelJoinedListener
import dev.kotlinautas.twitch4k.interfaces.listener.OnPrivateMessageListener
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.reflect.KClass

private const val TWITCH_IRC_SERVER = "irc.chat.twitch.tv"
private const val TWITCH_IRC_PORT = 6667

class Twitch4K(val username: String, val token: String, val channels: List<String>) : ISubscribable {

    private val logger = LoggerFactory.getLogger("[TWITCH4K]")
    private val send: (String) -> Unit = { message -> AsyncEventBus.dispatch(OnSendMessageEvent(message)) }
    private val say: (String, String) -> Unit =
        { channel, text -> AsyncEventBus.dispatch(OnSendMessageEvent(TwitchMessages.privMessage(channel, text))) }

    private var onChannelJoinedListener: OnChannelJoinedListener? = null
    private var onPrivateMessageListener: OnPrivateMessageListener? = null

    fun start() {
        AsyncEventBus.register(this)
        val socket = createSocket()

        if (socket.isConnected) {
            val twitchReaderRunnable = createAndStartTwitchReaderThread(socket, channels)
            val twitchWriterRunnable = createAndStartTwitchWriterThread(socket)

            setupConnection()

            twitchReaderRunnable.join()
            twitchWriterRunnable.join()
        }
    }

    private fun createAndStartTwitchWriterThread(socket: Socket): Thread {
        return Thread(TwitchWriterRunnable(socket)).also { it.start() }
    }

    private fun createAndStartTwitchReaderThread(socket: Socket, channels: List<String>): Thread {
        return Thread(TwitchReaderRunnable(socket, channels)).also { it.start() }
    }

    private fun setupConnection() {
        // Register Capabilities
        send(TwitchMessages.tagsCapability())
        send(TwitchMessages.commandsCapability())
        send(TwitchMessages.membershipCapability())

        // Send authentication credentials
        send(TwitchMessages.passMessage(token))
        send(TwitchMessages.nickMessage(username))
    }

    private fun createSocket(): Socket {
        val socket = Socket().apply {
            receiveBufferSize = 4096
            sendBufferSize = 4096
            reuseAddress = true
            tcpNoDelay = true
        }

        return socket.also {
            logger.info("Conectando ao host $TWITCH_IRC_SERVER:$TWITCH_IRC_PORT")
            it.connect(InetSocketAddress(TWITCH_IRC_SERVER, TWITCH_IRC_PORT))
        }
    }

    fun setOnConnectedListener(listener: OnChannelJoinedListener) {
        this.onChannelJoinedListener = listener
    }

    fun setOnPrivateMessageListener(listener: OnPrivateMessageListener) {
        this.onPrivateMessageListener = listener
    }

    override fun handle(event: IEvent<*>) {
        when (event::class) {
            OnChannelJoinEvent::class -> {
                val status = event.getData() as ChannelStatus
                onChannelJoinedListener?.onConnected(status, say)
            }

            OnPrivateMessageEvent::class -> {
                val privateMessage = event.getData() as PrivateMessage
                onPrivateMessageListener?.onPrivateMessage(privateMessage, say)
            }
        }
    }

    override fun supports(): Set<KClass<*>> {
        return setOf(
            OnChannelJoinEvent::class,
            OnPrivateMessageEvent::class
        )
    }

}





















