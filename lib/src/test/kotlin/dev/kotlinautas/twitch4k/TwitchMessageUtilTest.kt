package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.mock.TwitchMessageUtilMock
import dev.kotlinautas.twitch4k.util.TwitchMessageUtil
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe

class TwitchMessageUtilTest : ShouldSpec({
    context("O objeto TwitchMessageUtil") {
        should("deve ser capaz de converter uma string de tags em um map") {
            val tags = TwitchMessageUtilMock.getStringOfTags()
            val expected = TwitchMessageUtilMock.getMapOfTags()
            TwitchMessageUtil.parseTags(tags) shouldContainExactly expected
        }

        should("deve ser capaz de converter uma string de parâmetros para uma lista de strings"){
            val params = TwitchMessageUtilMock.getStringOfParams()
            val expected = TwitchMessageUtilMock.getListOfParams()

            TwitchMessageUtil.parseParams(params) shouldBe expected
        }

        should("deve ser capaz de manipular uma mensagem com tags da Twitch"){
            val message = TwitchMessageUtilMock.getStringOfRawMessageWithTags()
            val expected = TwitchMessageUtilMock.getRawMessageWithTags()

            TwitchMessageUtil.parse(message) shouldBe expected
        }

        should("deve ser capaz de manipular uma mensagem sem tags da Twitch"){
            val message = TwitchMessageUtilMock.getStringOfRawMessageWithoutTags()
            val expected = TwitchMessageUtilMock.getRawMessageWithoutTags()

            TwitchMessageUtil.parse(message) shouldBe expected
        }

        should("deve ser capaz de manipular uma mensagem sem tags e sem prefixo da Twitch"){
            val message = TwitchMessageUtilMock.getStringOfRawMessageWithoutTagsAndPrefix()
            val expected = TwitchMessageUtilMock.getRawMessageWithoutTagsAndPrefix()

            TwitchMessageUtil.parse(message) shouldBe expected
        }
    }
})