package dev.kotlinautas.twitch4k.components

import org.slf4j.LoggerFactory
import java.io.OutputStream
import java.util.Queue

class TwitchSender(
    private val outputStream: OutputStream,
    private val queue: Queue<String>
) : Runnable {

    private val logger = LoggerFactory.getLogger("TWITCH_SENDER")

    override fun run() {
        val bufferedWriter = outputStream.bufferedWriter()
        while (true) {
            if (queue.isNotEmpty()) {
                val message = queue.poll()
                bufferedWriter.write(message)
                bufferedWriter.flush()

                logger.info("Enviando mensagem: ${message.substringBefore("\r")}")
            }
        }
    }
}