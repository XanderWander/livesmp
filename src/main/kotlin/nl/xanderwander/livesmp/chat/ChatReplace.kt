package nl.xanderwander.livesmp.chat

class ChatReplace {

    private val replace = hashMapOf(
        Pair(":copper_ingot:", "\uF702"),
        Pair(":copper_block:", "\uF701"),
        Pair(":copper_ore:",   "\uF700"),
    )

    fun replace(str: String): String {
        var text = str
        for ((key, value) in replace) {
            text = text.replace(key, value)
        }
        return text
    }

}