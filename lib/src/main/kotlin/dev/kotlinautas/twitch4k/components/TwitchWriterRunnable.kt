package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.AsyncEventBus
import dev.kotlinautas.twitch4k.interfaces.bus.IEvent
import dev.kotlinautas.twitch4k.interfaces.bus.ISubscribable
import dev.kotlinautas.twitch4k.interfaces.event.OnConnectedEvent
import dev.kotlinautas.twitch4k.interfaces.event.OnSendMessageEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.net.Socket
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.reflect.KClass

class TwitchWriterRunnable(val socket: Socket) : Runnable, ISubscribable {

    private val logger = LoggerFactory.getLogger("[TWITCH4K-WRITER]")
    private val queue: Queue<String> = ConcurrentLinkedQueue()


    override fun run() = runBlocking {
        AsyncEventBus.register(this@TwitchWriterRunnable)

        while (true) {
            if (queue.isNotEmpty()) {
                socket
                    .getOutputStream()
                    .write(queue.poll().toByteArray())

                socket
                    .getOutputStream()
                    .flush()
            }
        }
    }

    override fun handle(event: IEvent<*>) {
        when (event::class) {
            OnSendMessageEvent::class -> queue.add(event.getData() as String)
        }
    }

    override fun supports(): Set<KClass<*>> {
        return setOf(
            OnSendMessageEvent::class
        )
    }

}