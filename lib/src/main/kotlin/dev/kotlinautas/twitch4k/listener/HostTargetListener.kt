package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.types.HostTargetType

interface HostTargetListener {

    fun onHostTargetListener(type: HostTargetType, channel: String, msg: String, viewers: String)

}