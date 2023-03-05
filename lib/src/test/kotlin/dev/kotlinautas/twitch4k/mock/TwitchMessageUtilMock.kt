package dev.kotlinautas.twitch4k.mock

import dev.kotlinautas.twitch4k.entity.message.RawMessage

object TwitchMessageUtilMock {

    fun getStringOfTags() =
        "@badge-info=;badges=broadcaster/1;client-nonce=459e3142897c7a22b7d275178f2259e0;color=#0000FF;display-name=lovingt3s;emote-only=1;emotes=62835:0-10;first-msg=0;flags=;id=885196de-cb67-427a-baa8-82f9b0fcd05f;mod=0;room-id=713936733;subscriber=0;tmi-sent-ts=1643904084794;turbo=0;user-id=713936733;user-type="

    fun getStringOfParams() = "#lovingt3s :bleedPurple"

    fun getStringOfRawMessageWithTags() =
        "@badge-info=;badges=broadcaster/1;client-nonce=459e3142897c7a22b7d275178f2259e0;color=#0000FF;display-name=lovingt3s;emote-only=1;emotes=62835:0-10;first-msg=0;flags=;id=885196de-cb67-427a-baa8-82f9b0fcd05f;mod=0;room-id=713936733;subscriber=0;tmi-sent-ts=1643904084794;turbo=0;user-id=713936733;user-type= :lovingt3s!lovingt3s@lovingt3s.tmi.twitch.tv PRIVMSG #lovingt3s :bleedPurple"

    fun getStringOfRawMessageWithoutTags() = ":bar!bar@bar.tmi.twitch.tv JOIN #twitchdev"

    fun getStringOfRawMessageWithoutTagsAndPrefix(): String = "PING :tmi.twitch.tv"

    fun getMapOfTags(): Map<String, String> {
        return hashMapOf(
            "badge-info" to "",
            "badges" to "broadcaster/1",
            "client-nonce" to "459e3142897c7a22b7d275178f2259e0",
            "color" to "#0000FF",
            "display-name" to "lovingt3s",
            "emote-only" to "1",
            "emotes" to "62835:0-10",
            "first-msg" to "0",
            "flags" to "",
            "id" to "885196de-cb67-427a-baa8-82f9b0fcd05f",
            "mod" to "0",
            "room-id" to "713936733",
            "subscriber" to "0",
            "tmi-sent-ts" to "1643904084794",
            "turbo" to "0",
            "user-id" to "713936733",
            "user-type" to ""
        )
    }

    fun getListOfParams(): List<String> {
        return listOf("#lovingt3s", ":bleedPurple")
    }

    fun getRawMessageWithTags(): RawMessage {
        return RawMessage(
            getStringOfRawMessageWithTags(),
            getMapOfTags(),
            prefix = ":lovingt3s!lovingt3s@lovingt3s.tmi.twitch.tv",
            command = "PRIVMSG",
            getListOfParams()
        )
    }

    fun getRawMessageWithoutTags(): RawMessage {
        return RawMessage(
            getStringOfRawMessageWithoutTags(),
            mapOf(),
            prefix = ":bar!bar@bar.tmi.twitch.tv",
            command = "JOIN",
            listOf("#twitchdev")
        )
    }

    fun getRawMessageWithoutTagsAndPrefix(): RawMessage {
        return RawMessage(
            getStringOfRawMessageWithoutTagsAndPrefix(),
            mapOf(),
            prefix = "",
            command = "PING",
            listOf(":tmi.twitch.tv")
        )
    }
}