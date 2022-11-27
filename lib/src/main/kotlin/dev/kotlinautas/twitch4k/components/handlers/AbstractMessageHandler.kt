package dev.kotlinautas.twitch4k.components.handlers

import org.slf4j.LoggerFactory

abstract class AbstractMessageHandler : MessageHandler {
    val logger = LoggerFactory.getLogger("TWITCH_HANDLER")
}