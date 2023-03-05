package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.entity.RawMessage
import dev.kotlinautas.twitch4k.util.IRCMessageUtil
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields

class IRCMessageUtilTest : ShouldSpec({
    context("O object IRCMessageUtil") {

        should("Realiza o parser de uma mensagem IRC com o command e params"){
            val ircMessage = "PING :tmi.twitch.tv"

            val expected = RawMessage.create {
                raw = ircMessage
                command = "PING"
                params.add(":tmi.twitch.tv")
            }

            IRCMessageUtil.parseIrcMessage(ircMessage) shouldBeEqualToComparingFields expected
        }

        should("Realizar o parser de uma mensagem IRC com prefix, command e params") {
            val ircMessage = ":foo!foo@foo.tmi.twitch.tv JOIN #bar"
            val expected = RawMessage.create {
                raw = ircMessage
                prefix = ":foo!foo@foo.tmi.twitch.tv"
                command = "JOIN"
                params.add("#bar")
            }

            IRCMessageUtil.parseIrcMessage(ircMessage) shouldBeEqualToComparingFields expected
        }

        should("Realizar o parser de uma mensagem IRC com tags, prefix, command e params") {
            val ircMessage =
                "@badge-info=;badges=staff/1,broadcaster/1,turbo/1;color=#008000;display-name=ronni;emotes=;id=db25007f-7a18-43eb-9379-80131e44d633;login=ronni;mod=0;msg-id=resub;msg-param-cumulative-months=6;msg-param-streak-months=2;msg-param-should-share-streak=1;msg-param-sub-plan=Prime;msg-param-sub-plan-name=Prime;room-id=12345678;subscriber=1;system-msg=ronni\\shas\\ssubscribed\\sfor\\s6\\smonths!;tmi-sent-ts=1507246572675;turbo=1;user-id=87654321;user-type=staff :tmi.twitch.tv USERNOTICE #dallas :Great stream -- keep it up!"
            val expected = RawMessage.create {
                raw = ircMessage
                tags["badge-info"] = ""
                tags["badges"] = "staff/1,broadcaster/1,turbo/1"
                tags["color"] = "#008000"
                tags["display-name"] = "ronni"
                tags["emotes"] = ""
                tags["id"] = "db25007f-7a18-43eb-9379-80131e44d633"
                tags["login"] = "ronni"
                tags["mod"] = "0"
                tags["msg-id"] = "resub"
                tags["msg-param-cumulative-months"] = "6"
                tags["msg-param-streak-months"] = "2"
                tags["msg-param-should-share-streak"] = "1"
                tags["msg-param-sub-plan"] = "Prime"
                tags["msg-param-sub-plan-name"] = "Prime"
                tags["room-id"] = "12345678"
                tags["subscriber"] = "1"
                tags["system-msg"] = "ronni\\shas\\ssubscribed\\sfor\\s6\\smonths!"
                tags["tmi-sent-ts"] = "1507246572675"
                tags["turbo"] = "1"
                tags["user-id"] = "87654321"
                tags["user-type"] = "staff"
                prefix = ":tmi.twitch.tv"
                command = "USERNOTICE"
                params.add("#dallas")
                params.add(":Great stream -- keep it up!")
            }

            IRCMessageUtil.parseIrcMessage(ircMessage) shouldBeEqualToComparingFields expected
        }

    }
})