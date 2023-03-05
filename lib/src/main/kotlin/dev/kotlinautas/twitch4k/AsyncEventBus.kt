package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.interfaces.bus.IEvent
import dev.kotlinautas.twitch4k.interfaces.bus.IEventBus
import dev.kotlinautas.twitch4k.interfaces.bus.ISubscribable
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.LinkedList


object AsyncEventBus: IEventBus {

    private val subscribers = LinkedList<ISubscribable>()

    override fun register(ISubscribable: ISubscribable) {
        subscribers.add(ISubscribable)
    }

    override fun dispatch(event: IEvent<*>) = runBlocking {
        subscribers
            .filter { subscriber -> subscriber.supports()!!.contains(event::class) }
            .forEach { subscriber -> launch { subscriber.handle(event) } }
    }

    override fun getSubscribers(): List<ISubscribable> = subscribers
}