package dev.kotlinautas.twitch4k.entity

class RawMessage private constructor(
    val raw: String,
    val tags: Map<String, String>,
    val prefix: String,
    val command: String,
    val params: List<String>
) {

    data class Builder(
        private var raw: String? = null,
        private var tags: MutableMap<String, String> = mutableMapOf(),
        private var prefix: String = "",
        private var command: String? = null,
        private val params: MutableList<String> = mutableListOf()
    ) {
        fun setRaw(rawMessage: String) = apply { this.raw = rawMessage }
        fun addTag(key: String, value: String) = apply { this.tags[key] = value }
        fun setPrefix(prefix: String) = apply { this.prefix = prefix }
        fun setCommand(command: String) = apply { this.command = command }
        fun addParam(param: String) = apply { this.params.add(param) }
        fun build(): RawMessage {

            if (raw == null) throw NullPointerException("A propriedade raw não pode ser nula")
            if (command == null) throw NullPointerException("A propriedade prefix não pode ser nula")

            return RawMessage(raw!!, tags.toMap(), prefix, command!!, params)

        }
    }

}