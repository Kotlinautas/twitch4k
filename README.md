# Twitch4K

## O que é o Twitch4K
O Twitch4K é uma biblioteca escrita na linguagem de programação Kotlin que permite a criação de `bots` voltados ao chat da Twitch. O intuito de escrever esta biblioteca é de compreender melhor como a infraestrutura de char da Twitch funcionaa, praticar a construção de um cliente IRC com Kotlin e possibilitar a criação de ferramentas visando melhorar a experiência das pessoas que assistem lives na Twitch através de `chatbots`

## Como utilizar a biblioteca
Para utilizar a biblioteca será necessário executar os seguintes passos:

1. Realizar o clone projeto com o seguinte comando:
```bash
git clone git@github.com:Kotlinautas/twitch4k.git
```
2. Entrar no diretório do projeto:
```bash
cd twitch4k
```
3. Construir e publicar para o repositório Maven local com o comando:

Para Linux e MacOS
```bash
./gradlew publishToMavenLocal
```

Para Windows:
```bash
./gradlew.bat publishToMavenLocal
```
4. Após terminar o processo de construção e compilação, declare a biblioteca no `build.gradle` do seu projeto realizando as seguintes alterações:
```kotlin
// ...
// ...
// ...

repositories {
    mavenLocal()
    // mavenCentral()
}

// ...
// ...
// ...

dependencies {
    implementation("dev.kotlinautas:twitch4k:1.0")
    // ...
    // ...
    // ...
}
```

## Exemplo de código

É necessário obtero o token `OAuth`. O mesmo pode ser obtido [AQUI](https://twitchapps.com/tmi/). Realize o _login_ com as credenciais da conta do _bot_ e, em seguida, será apresentado o `token` de acesso da conta do _bot_. O `tokem` possui o seguinte formato **oauth:xxyyzz112233**, copie a _string_ e utilize-a para realizar a autenticação do bot.

O trecho de código abaixo exemplifica como utilizar a biblioteca. Lembre-se de realizar os devidos `imports` no código abaixo (os mesmos foram retirados para deixar o exemplo enxuto).

```kotlin
class Bot : OnReceivedChatMessageListener {
    override fun onReceived(message: ChatMessage, chat: Chat) {
        
        // Verifica se a mensagem recebia começa com !teste. Caso positivo o bot irá responder com a mesnagem "Teste" no chat da Twitch
        if(message.text.startsWith("!teste")){
            chat.send(message.channel, "Teste")
        }
    }
}

fun main() {

    // Variável de ambiente com a conta do bot
    val username = System.getenv("TWITCH_BOT_USERNAME")
    // Variável de ambiente com o token OAuth de acesso à Twitch
    val token = System.getenv("TWITCH_BOT_TOKEN")

    val bot = Bot()

    // Criação da instância da biblioteca 
    val t4k = Twitch4K(
        
        // Nome de usuário da conta do bot
        username = username,
        
        // Token oauth da conta do bot
        // (vide texto acima para maiores informações)
        token = token,
        
        // Lista de canais da Twitch
        channels = listOf("canal_01, canal_02, canal_03")
    )

    // Registra o evento de mensagem recebida de algum chat registrado
    t4k.setOnReceivedChatMessageListener(bot)

    // Inicia o Bot
    t4k.start()
}
```

## Créditos
- Bruno Lopes - brunolopesjn@gmail.com
- Felipe Nathan Campigoto - ncampigoto@gmail.com