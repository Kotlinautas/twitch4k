package dev.kotlinautas.twitch4k.entity

data class RoomState(
    val channelId: Int,
    val channelName: String,
    val tags: MutableMap<String, String>
) {

    val isEmoteOnlyMode get() = tags["emote-only"] != "0"

    val isFollowersOnlyMode get() = tags["followers-only"] != "0" && tags["followers-only"] != "-1"

    val isSlowMode get() = tags["slow"] != "0"

    val isSubsOnlyMode get() = tags["subs-only"] != "0"

    override fun toString(): String {
        return """
        Estado do canal $channelName ($channelId)
        Modo apenas emote: ${if (isEmoteOnlyMode) "Ativado" else "Desativado"}
        Modo apenas seguidores: ${if (isFollowersOnlyMode) "Ativado" else "Desativado"}
        Modo lento: ${if (isSlowMode) "Ativado" else "Desativado"}
        Modo apenas inscritos: ${if (isSubsOnlyMode) "Ativado" else "Desativado"}
        """.trimIndent()
    }
}