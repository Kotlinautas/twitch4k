package dev.kotlinautas.twitch4k

import dev.kotlinautas.entity.RawMessage
import dev.kotlinautas.twitch4k.listener.ConnectedListener
import dev.kotlinautas.twitch4k.listener.PrivateMessageListener
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

class AdaLovelanceHanlder : ConnectedListener, PrivateMessageListener {

    private val logger = LoggerFactory.getLogger("AdalovelanceBot")

    override fun onConnected() {
        logger.info("AdaLovelanceBot Conectada!")
    }

    override fun onPrivateMessageListener(
        username: String,
        message: String,
        rawMessage: RawMessage,
        dispatcher: Dispatcher
    ) {
        println("<$username>: $message")
    }
}

fun main() = runBlocking {

    val username = System.getenv("TWITCH_BOT_USERNAME")
    val oauthToken = System.getenv("TWITCH_BOT_TOKEN")

    val twitch4K = Twitch4K.Builder()
        .setUsername(username)
        .setOauthToken(oauthToken)
        .connectChannel("profbrunolopes")
        .build()

    val handler = AdaLovelanceHanlder()

    twitch4K.setOnConnectedListener(handler)
    twitch4K.setOnPrivateMessageListener(handler)

    twitch4K.connect()

}