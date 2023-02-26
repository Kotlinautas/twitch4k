package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.entities.ChannelStatus
import dev.kotlinautas.twitch4k.entities.message.PrivateMessage
import dev.kotlinautas.twitch4k.interfaces.listener.OnChannelJoinedListener
import dev.kotlinautas.twitch4k.interfaces.listener.OnPrivateMessageListener

class App : OnChannelJoinedListener, OnPrivateMessageListener {
    override fun onConnected(status: ChannelStatus, say: (channel: String, text: String) -> Unit) {
        say(status.channel, "A mãe tá on!")
    }

    override fun onPrivateMessage(message: PrivateMessage, say: (channel: String, text: String) -> Unit) {
        if(message.text.startsWith("!oi") && message.user.isSubscriber){
            say(message.channel, "Olá ${message.user.displayName}! Espero que você esteja bem!")
        }

        if(message.text.startsWith("!ola") && message.user.isBroadcaster){
            say(message.channel, "Olá streamer maravilhoso dessa galacta!")
        }
    }
}

fun main() {

    val app = App()
    val username = System.getenv("TWITCH_BOT_USERNAME")
    val token = System.getenv("TWITCH_BOT_TOKEN")

    val twitch4K = Twitch4K(
        username = username,
        token = token,
        channels = listOf("profbrunolopes")
    )

    twitch4K.setOnConnectedListener(app)
    twitch4K.setOnPrivateMessageListener(app)

    twitch4K.start()

}