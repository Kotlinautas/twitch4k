package dev.kotlinautas.twitch4k.util

import dev.kotlinautas.twitch4k.entities.User
import dev.kotlinautas.twitch4k.entities.message.IrcMessage

object IRCMessageUtil {

    fun parseIrcMessage(message: String): IrcMessage {
        return IrcMessage.create {
            raw = message
            message
                .extractTags(this)
                .extractPrefix(this)
                .extractCommand(this)
                .extractParams(this)
        }
    }

    fun parseUser(ircMessage: IrcMessage): User {
        return User(
            id = ircMessage.tags.getUserId(),
            displayName = ircMessage.tags.getDisplayName(),
            color = ircMessage.tags.getColor(),
            badges = parseBadges(ircMessage.tags.getBadges()),
            isFirstMessage = ircMessage.tags.isFirstMessage(),
            isModerator = ircMessage.tags.isModerator(),
            isSubscriber = ircMessage.tags.isSubscriber(),
        )
    }

    private fun parseBadges(badges: String): Map<String, Int> {
        val badgesMap = mutableMapOf<String, Int>()
        if(badges.isNotEmpty()){
            badges
                .split(",")
                .forEach {
                    val pair = it.split("/", limit = 2)
                    badgesMap[pair.first()] = pair.last().toInt()
                }
        }
        return badgesMap
    }

    private fun String.extractParams(builder: IrcMessage.Builder): String {
        var remaining = this
        while (remaining.isNotEmpty()) {
            remaining = when {
                remaining.startsWith(":") -> {
                    builder.params.add(remaining)
                    ""
                }

                else -> {
                    val aux = remaining.split(" ", limit = 2)
                    builder.params.add(aux.first())
                    if (aux.size == 2) aux.last() else ""
                }
            }
        }
        return ""
    }

    private fun String.extractCommand(builder: IrcMessage.Builder): String {
        val aux = this.split(" ", limit = 2)
        builder.command = aux.first()
        return aux.last()
    }

    private fun String.extractPrefix(builder: IrcMessage.Builder): String {
        if (this.startsWith(":")) {
            val aux = this.split(" ", limit = 2)
            builder.prefix = aux.first()
            return aux.last()
        }
        return this
    }

    private fun String.extractTags(builder: IrcMessage.Builder): String {
        if (this.startsWith("@")) {
            val aux = this.substring(1).split(" ", limit = 2)
            aux.first().split(";").forEach {
                val tag = it.split("=")
                builder.tags[tag.first()] = tag.last()
            }
            return aux.last()
        }
        return this
    }
}