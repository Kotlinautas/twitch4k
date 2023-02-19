package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.interfaces.bus.IEvent
import dev.kotlinautas.twitch4k.interfaces.bus.ISubscribable
import dev.kotlinautas.twitch4k.interfaces.event.OnConnectedEvent
import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.components.TwitchReaderRunnable
import dev.kotlinautas.twitch4k.components.TwitchWriterRunnable
import dev.kotlinautas.twitch4k.entities.ChannelStatus
import dev.kotlinautas.twitch4k.interfaces.event.OnSendMessageEvent
import dev.kotlinautas.twitch4k.interfaces.listener.OnConnectedListener
import org.slf4j.LoggerFactory
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.reflect.KClass

private const val TWITCH_IRC_SERVER = "irc.chat.twitch.tv"
private const val TWITCH_IRC_PORT = 6667

class Twitch4K(val username: String, val token: String, val channels: List<String>) : ISubscribable {

    private val logger = LoggerFactory.getLogger("[TWITCH4K]")
    private val send: (String) -> Unit = { message -> AsyncEventBus.dispatch(OnSendMessageEvent(message)) }

    private lateinit var onConnectedListener: OnConnectedListener

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

    fun setOnConnectedListener(listener: OnConnectedListener) {
        this.onConnectedListener = listener
    }

    override fun handle(event: IEvent<*>) {
        when (event::class) {
            OnConnectedEvent::class -> {
                val status = event.getData() as ChannelStatus
                onConnectedListener.onConnected(status, send)
            }
        }
    }

    override fun supports(): Set<KClass<*>> {
        return setOf(
            OnConnectedEvent::class
        )
    }

}





















