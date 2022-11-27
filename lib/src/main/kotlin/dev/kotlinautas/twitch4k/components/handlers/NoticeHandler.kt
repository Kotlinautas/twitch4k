package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage

class NoticeHandler : AbstractMessageHandler() {

    override val codes: Array<String> = arrayOf("NOTICE")

    override fun handle(rawMessage: RawMessage) {
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
}