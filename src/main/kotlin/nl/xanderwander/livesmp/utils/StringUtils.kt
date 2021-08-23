package nl.xanderwander.livesmp.utils

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

    }

}