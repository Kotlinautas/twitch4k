package dev.kotlinautas.parser

import dev.kotlinautas.entity.RawMessage

private const val AT_CHAR = "@"
private const val COLON_CHAR = ":"
private const val SPACE_CHAR = " "

object RawMessageParser {

    fun parse(data: String): RawMessage? {

        val builder = RawMessage.Builder()
        builder.setRaw(data)

        // Position e nextSpace são utilizados pelo parser como referencia...
        var position = 0
        var nextSpace: Int

        // A primeira coisa a ser verificada é se a mensagem esta no formato IRCv3.2 de tags
        // http://ircv3.atheme.org/specification/message-tags-3.2
        if (data.startsWith(AT_CHAR, position)) {

            nextSpace = data.indexOf(SPACE_CHAR, position)

            // Mensagem mal formada
            if (nextSpace == -1) return null

            // A string que contêm as tags é dividida em substrings pelo ponto e vírgula
            val rawTags = data.substring(1, nextSpace).split(";")
            rawTags.forEach {
                // Tags estão no formato key=value, então quebraremos em substrigs baseado no simbolo de igual
                // Se uma tag não possuir o simbolo de igual, então o valor da tag é o valor true
                val pair = it.split("=")
                if (pair[1] == "")
                    builder.addTag(pair[0], "true")
                else
                    builder.addTag(pair[0], pair[1])
            }

            position = nextSpace + 1

        }

        // Ignora qualquer espaços em branco à direita
        while (data.startsWith(SPACE_CHAR, position)) {
            position += 1
        }

        // Extrai o prefixo da mensagem se o mesmo estiver presente
        // Prefixos são precedidos por um simbolo de dois pontos
        if (data.startsWith(COLON_CHAR, position)) {

            nextSpace = data.indexOf(SPACE_CHAR, position)

            // Mensagem mal formada
            if (nextSpace == -1) {
                return null
            }

            builder.setPrefix(data.substring(position + 1, nextSpace))
            position = nextSpace + 1

            // Ignora qualquer espaços em branco à direita
            while (data.startsWith(SPACE_CHAR, position)) {
                position += 1
            }

        }

        nextSpace = data.indexOf(SPACE_CHAR, position)

        // Se não existe mais nenhum espaço em branco a esqueda, extrair tudo
        // da posição atual até o fim da string como comando
        if (nextSpace == -1) {
            if (data.length > position) {
                builder.setCommand(data.substring(position))
                return builder.build()
            }
            return null
        }

        // Caso contrário, o comando está na posição atual até o proximo espaço.
        // Depois do comando, ainda esperamos encontrar alguns parâmetros
        builder.setCommand(data.substring(position, nextSpace))

        position = nextSpace + 1

        // Ignora qualquer espaços em branco à direita
        while (data.startsWith(SPACE_CHAR, position)) {
            position += 1
        }

        while (position < data.length) {

            nextSpace = data.indexOf(SPACE_CHAR, position)

            // Se um caractere é um símbolo de dois pontos, nós temos que pegar
            // o parâmetro da direita. Neste ponto, não existe nenhum parâmetro
            // extra, então podemos colocar tudo depois do símbolo de dois pontos
            // até o fim da string para a lista de parâmetros e sair do laço de repetição.
            if (data.startsWith(COLON_CHAR, position)) {
                builder.addParams(data.substring(position + 1))
                break
            }

            // Se ainda temos alguns espaços em branco
            if (nextSpace != -1) {

                // Colocar o que estiver na posição atual e o próximo espaço para
                // a lista de parâmetros
                builder.addParams(data.substring(position, nextSpace))
                position = nextSpace + 1

                // Ignora qualquer espaços em branco à direita
                while (data.startsWith(SPACE_CHAR, position)) {
                    position += 1
                }

                continue

            }

            // Caso não tiver nenhum espaço em branco e nenhum parâmetro a direita,
            // colocar todo o restante para a lista de parâmetros
            if(nextSpace == -1){
                builder.addParams(data.substring(position))
                break
            }

        }

        return builder.build()

    }

}