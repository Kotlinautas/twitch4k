package dev.kotlinautas.twitch4k.entity

class RawMessage private constructor(
    val raw: String,
    val tags: Map<String, String>,
    val prefix: String,
    val command: String,
    val params: List<String>
) {

    override fun toString(): String {
        return "[tags=$tags, prefix=$prefix, command=$command, params=$params]"
    }

    companion object {
        @JvmStatic
        fun create(operations: Builder.() -> Unit): RawMessage{
            val builder = Builder()
            builder.operations()
            return builder.build()
        }

    }

    class Builder {

        var raw: String? = null
        var tags: MutableMap<String, String> = mutableMapOf()
        var prefix: String = ""
        var command: String? = null
        val params: MutableList<String> = mutableListOf()

//        fun setRaw(rawMessage: String) = apply { this.raw = rawMessage }
//        fun addTag(key: String, value: String) = apply { this.tags[key] = value }
//        fun setPrefix(prefix: String) = apply { this.prefix = prefix }
//        fun setCommand(command: String) = apply { this.command = command }
//        fun addParam(param: String) = apply { this.params.add(param) }
        fun build(): RawMessage {

            if (raw == null) throw NullPointerException("A propriedade raw não pode ser nula")
            if (command == null) throw NullPointerException("A propriedade prefix não pode ser nula")

            return RawMessage(raw!!, tags.toMap(), prefix, command!!, params)

        }
    }

}