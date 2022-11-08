package dev.kotlinautas.twitch4k.components.handlers

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.entity.RoomState

class StateHandler : AbstractMessageHandler() {

    private val roomStateMap: MutableMap<Int, RoomState> = mutableMapOf()

    override val codes: Array<String> = arrayOf("USERSTATE", "ROOTSTATE")

    override fun handle(rawMessage: RawMessage) {
        val channelId = rawMessage.tags["room-id"]?.toInt() ?: 0
        val channelName = rawMessage.params.first()

        val roomState = roomStateMap.getOrDefault(
            channelId,
            RoomState(
                channelId,
                channelName,
                rawMessage.tags.toMutableMap())
        )

        rawMessage.tags.forEach { (key, value) ->
            if(key != "room-id") roomState.tags[key] = value
        }

        roomStateMap[channelId] = roomState
        logger.info(roomState.toString())
    }
}