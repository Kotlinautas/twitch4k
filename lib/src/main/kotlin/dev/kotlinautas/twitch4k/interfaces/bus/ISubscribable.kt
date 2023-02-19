package dev.kotlinautas.twitch4k.interfaces.bus

import dev.kotlinautas.twitch4k.interfaces.bus.IEvent
import kotlin.reflect.KClass

interface ISubscribable {
    fun handle(event: IEvent<*>)

    fun supports(): Set<KClass<*>>
}