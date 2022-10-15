package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.types.UserNoticeType

interface UserNoticeListener {

    fun onUserNoticeListener(type: UserNoticeType, channel: String, msgId: String, msg: String)

}