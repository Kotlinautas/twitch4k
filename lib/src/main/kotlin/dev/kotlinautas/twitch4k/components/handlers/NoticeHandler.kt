package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.interfaces.Sender

class NoticeHandler : AbstractMessageHandler() {

    override fun handle(message: RawMessage, sender: Sender) {
        when (message.tags["msg-id"]) {
            "emote_only_on" -> logger.info("O chat do canal ${message.params.first()} está no modo apenas emote")
            "emote_only_off" -> logger.info("O chat do canal ${message.params.first()} não está no modo apenas emote")
            "followers_on" -> logger.info("O chat do canal ${message.params.first()} está no modo apenas seguidores")
            "followers_off" -> logger.info("O chat do canal ${message.params.first()} não está no modo apenas seguidores")
            "slow_on" -> logger.info("O chat do canal ${message.params.first()} está no modo lento")
            "slow_off" -> logger.info("O chat do canal ${message.params.first()} não está no modo lento")
            "subs_on" -> logger.info("O chat do canal ${message.params.first()} está no modo apenas inscritos")
            "subs_off" -> logger.info("O chat do canal ${message.params.first()} não está no apenas inscritos")
        }
    }
}