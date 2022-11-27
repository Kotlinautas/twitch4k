package dev.kotlinautas.twitch4k.components.handlers

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractMessageHandler : MessageHandler {
    val logger: Logger = LoggerFactory.getLogger("TWITCH_HANDLER")
}