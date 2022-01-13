package nl.xanderwander.livesmp.reflection

import nl.xanderwander.livesmp.Main

class ReflectionUtil {

    companion object {
        fun invoke(obj: Any?, name: String, vararg parameters: Any): Any? {
            try {
                val parameterClasses = parameterClasses(*parameters)
                val clazzInstance = obj?.javaClass
                val method = clazzInstance?.getDeclaredMethod(name, *parameterClasses)
                return method?.invoke(obj, *parameters)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun invoke(className: String, methodName: String, vararg parameters: Any?): Any? {
            try {
                val parameterClasses = parameterClasses(*parameters)
                val clazzInstance = Class.forName(className)
                val method = clazzInstance.getDeclaredMethod(methodName, *parameterClasses)
                return method.invoke(null, *parameters)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun invoke(className: String, classPrefix: ClassPrefix, methodName: String, vararg parameters: Any?): Any? {
            val clazz = className(className, classPrefix)
            return invoke(clazz, methodName, *parameters)
        }

        fun invokeTyped(obj: Any?, methodName: String, vararg parameters: Pair<Any?, Class<*>>): Any? {
            try {
                val parametersTyped = parameterClassesTyped(*parameters)
                val parameterObjects = parametersTyped.first
                val parameterClasses = parametersTyped.second
                val clazzInstance = obj?.javaClass
                val method = clazzInstance?.getDeclaredMethod(methodName, *parameterClasses)
                return method?.invoke(null, *parameterObjects)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun invokeTyped(className: String, methodName: String, vararg parameters: Pair<Any?, Class<*>>): Any? {
            try {
                val parametersTyped = parameterClassesTyped(*parameters)
                val parameterObjects = parametersTyped.first
                val parameterClasses = parametersTyped.second
                val clazzInstance = Class.forName(className)
                val method = clazzInstance.getDeclaredMethod(methodName, *parameterClasses)
                return method.invoke(null, *parameterObjects)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun invokeTyped(className: String, classPrefix: ClassPrefix, methodName: String, vararg parameters: Pair<Any?, Class<*>>): Any? {
            val clazz = className(className, classPrefix)
            return invokeTyped(clazz, methodName, *parameters)
        }

        fun newInstance(className: String): Any? {
            try {
                val clazz = Class.forName(className)
                return clazz.getConstructor().newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun newInstance(className: String, classPrefix: ClassPrefix): Any? {
            val clazz = className(className, classPrefix)
            return newInstance(clazz)
        }

        fun getClass(className: String): Class<*>? {
            try {
                return Class.forName(className)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        fun getClass(className: String, classPrefix: ClassPrefix): Class<*>? {
            val clazz = className(className, classPrefix)
            return getClass(clazz)
        }

        private fun parameterClasses(vararg parameters: Any?): Array<Class<*>?> {
            val parameterTypes = arrayListOf<Class<*>?>()
            for (parameter in parameters) {
                parameterTypes.add(parameter?.javaClass)
            }
            return parameterTypes.toTypedArray()
        }

        private fun parameterClassesTyped(vararg parameters: Pair<Any?, Class<*>>): Pair<Array<Any?>, Array<Class<*>>> {
            val parameterObjects = arrayListOf<Any?>()
            val parameterTypes = arrayListOf<Class<*>>()
            parameters.forEach { (o, t) ->
                parameterObjects.add(o)
                parameterTypes.add(t)
            }
            return Pair(parameterObjects.toTypedArray(), parameterTypes.toTypedArray())
        }

        private fun className(name: String, prefix: ClassPrefix): String {
            return when(prefix) {
                ClassPrefix.NONE -> name
                ClassPrefix.NMS -> "net.minecraft.server.${Main.version}.$name"
                ClassPrefix.OBC -> "org.bukkit.craftbukkit.${Main.version}.$name"
            }
        }

    }

}