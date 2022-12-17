package dev.kotlinautas.twitch4k.entity

data class RoomState(
    val channelId: Int,
    val channelName: String,
    val tags: MutableMap<String, String>
) {

    val isEmoteOnlyMode get() = tags["emote-only"] == "0"

    val isFollowersOnlyMode get() = (tags["followers-only"]?.toIntOrNull() ?: -1) > 0

    val isSlowMode get() = tags["slow"] == "0"

    val isSubsOnlyMode get() = tags["subs-only"] == "0"

    override fun toString(): String {
        return """
        Estado do canal $channelName ($channelId)
        Modo apenas emote: ${isEmoteOnlyMode.toHumanReadable()}
        Modo apenas seguidores: ${isFollowersOnlyMode.toHumanReadable()}
        Modo lento: ${isSlowMode.toHumanReadable()}
        Modo apenas inscritos: ${isSubsOnlyMode.toHumanReadable()}
        """.trimIndent()
    }

    private fun Boolean.toHumanReadable() = if (this) "Ativado" else "Desativado"
}