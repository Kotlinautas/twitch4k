package dev.kotlinautas.twitch4k.components

import dev.kotlinautas.twitch4k.entity.RoomState
import dev.kotlinautas.twitch4k.interfaces.Sender
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import org.slf4j.LoggerFactory
import java.io.InputStream

class TwitchHandler(
    private val inputStream: InputStream,
    private val channels: List<String>,
    private val sender: Sender
) : Runnable {

    private val logger = LoggerFactory.getLogger("TWITCH_HANDLER")
    private val roomStateMap: MutableMap<Int, RoomState> = mutableMapOf()

    override fun run() {
        val bufferedReader = inputStream.bufferedReader()
        while (true) {
            val message = bufferedReader.readLine()
            if (message != null) {
                val rawMessage = IRCMessageUtil.parseRawMessage(message)
                //logger.info(rawMessage.toString())

                when (rawMessage.command) {

                    "001" -> {
                        logger.info("Autenticado com sucesso!")

                        // Registrando nas Twitch Capabilities
                        sender.sendMessage(TwitchMessages.membershipCommands())
                        sender.sendMessage(TwitchMessages.membershipCapability())
                        sender.sendMessage(TwitchMessages.membershipTags())

                        // Entrando nos canais
                        channels.forEach { channel ->
                            sender.sendMessage(TwitchMessages.joinMessage(channel))
                        }
                    }

                    "002",
                    "003",
                    "004",
                    "353",
                    "366",
                    "372",
                    "375",
                    "376" -> {
                    }

                    "CAP" -> {
                        if (rawMessage.params.contains("ACK")) {
                            when {
                                rawMessage.params.last()
                                    .contains("commands") -> logger.info("Recebido o ACK da Capability COMMANDS")

                                rawMessage.params.last()
                                    .contains("membership") -> logger.info("Recebido o ACK da Capability MEMBERSHIP")

                                rawMessage.params.last()
                                    .contains("tags") -> logger.info("Recebido o ACK da Capability TAGS")
                            }

                        }
                    }

                    "JOIN" -> {
                        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
                        logger.info("$chatter entrou no canal ${rawMessage.params.first()}")
                    }

                    "PART" -> {
                        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
                        logger.info("$chatter saiu no canal ${rawMessage.params.first()}")
                    }

                    "USERSTATE",
                    "ROOMSTATE" -> {
                        val channelId = rawMessage.tags["room-id"]?.toInt() ?: 0
                        val channelName = rawMessage.params.first()

                        val roomState = roomStateMap.getOrDefault(
                            channelId,
                            RoomState(
                                channelId,
                                channelName,
                                rawMessage.tags.toMutableMap()))

                        rawMessage.tags.forEach { (key, value) ->
                            if(key != "room-id") roomState.tags[key] = value
                        }

                        roomStateMap[channelId] = roomState
                        logger.info(roomState.toString())
                    }

                    "NOTICE" -> {
                        when (rawMessage.tags["msg-id"]) {
                            "emote_only_on" -> logger.info("O chat do canal ${rawMessage.params.first()} está no modo apenas emote")
                            "emote_only_off" -> logger.info("O chat do canal ${rawMessage.params.first()} não está no modo apenas emote")
                            "followers_on" -> logger.info("O chat do canal ${rawMessage.params.first()} está no modo apenas seguidores")
                            "followers_off" -> logger.info("O chat do canal ${rawMessage.params.first()} não está no modo apenas seguidores")
                            "slow_on" -> logger.info("O chat do canal ${rawMessage.params.first()} está no modo lento")
                            "slow_off" -> logger.info("O chat do canal ${rawMessage.params.first()} não está no modo lento")
                            "subs_on" -> logger.info("O chat do canal ${rawMessage.params.first()} está no modo apenas inscritos")
                            "subs_off" -> logger.info("O chat do canal ${rawMessage.params.first()} não está no apenas inscritos")
                        }
                    }

                    "PING" -> {
                        logger.info("Mensagem de PING recebida, respondendo com um PONG")
                        sender.sendMessage(TwitchMessages.pongMessage(rawMessage.params.first()))
                    }

                    "PRIVMSG" -> {
                        logger.info(rawMessage.toString())
                        val chatter = IRCMessageUtil.getChatterUsernameFromPrefix(rawMessage.prefix)
                        val channel = rawMessage.params.first().removePrefix("#")
                        val text = rawMessage.params.last().removePrefix(":")
                        logger.info("[$channel] $chatter: $text")
                        //TODO: Implementar um Observer aqui
                    }

                    else -> logger.error("Comando não esperado: ${rawMessage.command}")

                }

            }
        }
    }

}