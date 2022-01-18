package nl.xanderwander.livesmp.utils

import org.bukkit.Material

class StringUtils {

    companion object {

        fun removeIfFirst(a: String, d: String): String {
            if (a.startsWith(d)) {
                return a.substring(1)
            }
            return a
        }

        fun spaces(a: Int): String {
            var r = ""
            for (i in 0 until a) {
                r += " "
            }
            return r
        }

        fun capitalizeFirstLetters(text: String): String {
            val words = text.lowercase().split(" ")
            val final = arrayListOf<String>()
            for (word in words) {
                final.add(word[0].uppercase()+word.substring(1))
            }
            return final.joinToString(" ")
        }

    }

}