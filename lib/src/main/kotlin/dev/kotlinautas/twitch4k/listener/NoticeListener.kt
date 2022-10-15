package dev.kotlinautas.twitch4k.listener

import dev.kotlinautas.twitch4k.types.NoticeType

interface NoticeListener {
    fun onNoticeListener(type: NoticeType, channel: String, msgId: String, msg: String)
}