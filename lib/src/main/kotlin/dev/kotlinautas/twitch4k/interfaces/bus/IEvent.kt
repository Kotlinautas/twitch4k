package dev.kotlinautas.twitch4k.interfaces.bus

interface IEvent<T> {

    fun getData():T

}