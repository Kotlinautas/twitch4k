package dev.kotlinautas.twitch4k.entity

import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.interfaces.Sender

class Chat (private val channel: String, private val sender: Sender) {

    fun send(message:String){
        sender.sendMessage(TwitchMessages.privMessage(channel, message))
    }

}