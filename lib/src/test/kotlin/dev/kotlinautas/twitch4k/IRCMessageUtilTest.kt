package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.entity.RawMessage
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields

class IRCMessageUtilTest:ShouldSpec({
    context("O object IRCMessageUtil"){

        should("Realizar o parser de uma mensagem IRC com prefix, command e params"){
            val ircMessage = ":foo!foo@foo.tmi.twitch.tv JOIN #bar"
            val expected = RawMessage.Builder()
                .setRaw(ircMessage)
                .setPrefix(":foo!foo@foo.tmi.twitch.tv")
                .setCommand("JOIN")
                .addParam("#bar")
                .build()

            IRCMessageUtil.parseRawMessage(ircMessage) shouldBeEqualToComparingFields expected
        }

        should("Realizar o parser de uma mensagem IRC com tags, prefix, command e params") {
            val ircMessage = "@badge-info=;badges=staff/1,broadcaster/1,turbo/1;color=#008000;display-name=ronni;emotes=;id=db25007f-7a18-43eb-9379-80131e44d633;login=ronni;mod=0;msg-id=resub;msg-param-cumulative-months=6;msg-param-streak-months=2;msg-param-should-share-streak=1;msg-param-sub-plan=Prime;msg-param-sub-plan-name=Prime;room-id=12345678;subscriber=1;system-msg=ronni\\shas\\ssubscribed\\sfor\\s6\\smonths!;tmi-sent-ts=1507246572675;turbo=1;user-id=87654321;user-type=staff :tmi.twitch.tv USERNOTICE #dallas :Great stream -- keep it up!"
            val expected = RawMessage.Builder()
                .setRaw(ircMessage)
                .addTag("badge-info", "")
                .addTag("badges", "staff/1,broadcaster/1,turbo/1")
                .addTag("color", "#008000")
                .addTag("display-name", "ronni")
                .addTag("emotes", "")
                .addTag("id", "db25007f-7a18-43eb-9379-80131e44d633")
                .addTag("login", "ronni")
                .addTag("mod", "0")
                .addTag("msg-id", "resub")
                .addTag("msg-param-cumulative-months", "6")
                .addTag("msg-param-streak-months", "2")
                .addTag("msg-param-should-share-streak", "1")
                .addTag("msg-param-sub-plan", "Prime")
                .addTag("msg-param-sub-plan-name", "Prime")
                .addTag("room-id", "12345678")
                .addTag("subscriber", "1")
                .addTag("system-msg", "ronni\\shas\\ssubscribed\\sfor\\s6\\smonths!")
                .addTag("tmi-sent-ts", "1507246572675")
                .addTag("turbo", "1")
                .addTag("user-id", "87654321")
                .addTag("user-type", "staff")
                .setPrefix(":tmi.twitch.tv")
                .setCommand("USERNOTICE")
                .addParam("#dallas")
                .addParam(":Great stream -- keep it up!")
                .build()

            IRCMessageUtil.parseRawMessage(ircMessage) shouldBeEqualToComparingFields expected
        }
    }
})