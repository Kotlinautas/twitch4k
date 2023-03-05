package dev.kotlinautas.twitch4k

import dev.kotlinautas.twitch4k.mock.RawMessageConverterMock
import dev.kotlinautas.twitch4k.util.TwitchMessageUtil
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class RawMessageConverterTest : ShouldSpec({
    context("Uma instância de RawMessage"){
        should("possuir o valor PING no atributo command, deve retornar uma instância de PingMessage "){
            val message = RawMessageConverterMock.getPingRawMessage()
            val expected = RawMessageConverterMock.getPingMessage()

            TwitchMessageUtil.parse(message).toPingMessage() shouldBe expected
        }
        should("possuir o valor PRIVMSG no atributo command, deve retornar uma instancia de PrivateMessage"){
            false shouldBe true
        }
    }
})