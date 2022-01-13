package nl.xanderwander.livesmp.utils

import nl.xanderwander.livesmp.Main
import java.lang.reflect.Method

class ReflectUtils {

    companion object {

        fun logMethods(clazz: Class<*>) {
            for (method in clazz.declaredMethods) {
                logMethod(method)
            }
        }

        private fun logMethod(method: Method) {
            val methodName = method.name
            val methodReturnType = formattedReturnType(method.returnType)
            val methodParameters = formattedParameters(method)
            Main.instance.logger.info("$methodReturnType $methodName($methodParameters)")
        }

        private fun formattedReturnType(returnType: Class<*>): String {
            val returnTypeName = returnType.typeName
            if (returnTypeName == "void") return "void"
            return "public " + returnTypeName.split(".").last()
        }

        private fun formattedParameters(method: Method): String {
            val parameters = arrayListOf<String>()
            method.parameterTypes.forEach { parameters.add(it.name) }
            return parameters.joinToString(", ")
        }

    }

}