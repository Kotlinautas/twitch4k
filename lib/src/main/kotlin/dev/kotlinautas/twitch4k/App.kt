package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.components.TwitchMessages
import dev.kotlinautas.twitch4k.entities.ChannelStatus
import dev.kotlinautas.twitch4k.interfaces.event.OnConnectedEvent
import dev.kotlinautas.twitch4k.interfaces.listener.OnConnectedListener

class App : OnConnectedListener {
    override fun onConnected(status: ChannelStatus, send: (String) -> Unit) {
        println("Olá!")
        send(TwitchMessages.privMessage(status.channel, "A mãe ta on!"))
    }
}

fun main() {

    val app = App()

    val twitch4K = Twitch4K(
        username = "justinfan123123",
        token = "oauth:59301",
        channels = listOf("profbrunolopes")
    )

    twitch4K.setOnConnectedListener(app)
    twitch4K.start()

}