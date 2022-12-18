package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.entity.ChatMessage
import dev.kotlinautas.twitch4k.entity.User
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import java.util.Date

class ChatMessageTest : ShouldSpec({

    context("O objeto ChatMessage") {
        should("Deveria retornar uma string no formatoJSON") {

            val json = """{"id":"123","date":0,"channel":"mychannel","text":"coxinha123","user":{"id":123,"displayName":"JohnJoe","isSubscriber":false,"isModerator":false,"isFirstMessage":false}}""".trimIndent()

            val chatMessage = ChatMessage(
                "123",
                Date(0L),
                "mychannel",
                "coxinha123", User(
                    123L,
                    "JohnJoe",
                    isSubscriber = false,
                    isModerator = false,
                    isFirstMessage = false
                ))

            chatMessage.toJson() shouldBe json


        }
    }
})