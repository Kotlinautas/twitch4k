package dev.kotlinautas.twitch4k.interfaces.bus

interface IEventBus {

    fun register(ISubscribable: ISubscribable)

    fun dispatch(IEvent: IEvent<*>)

    fun getSubscribers(): List<ISubscribable>

}