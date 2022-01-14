package nl.xanderwander.livesmp.objects

import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

data class Vector(
    var x: Double = 0.001,
    var y: Double = 0.001,
    var z: Double = 0.001,
) {

    fun set(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    fun copy(): Vector {
        return Vector(x, y, z)
    }

    fun multiply(d: Double): Vector {
        this.x *= d
        this.y *= d
        this.z *= d
        return this
    }

    fun add(other: Vector): Vector {
        this.x += other.x
        this.y += other.y
        this.z += other.z
        return this
    }

    fun dot(other: Vector): Double {
        return x * other.x + y * other.y + z * other.z
    }

    fun length(): Double {
        return sqrt(x*x + y*y + z*z)
    }

//    fun angle(other: Vector): Double {
//        val dot = MathUtils.constaint(dot(other) / (length() * other.length()), -1.0, 1.0)
//        return acos(dot)
//    }

    fun normalize(): Vector {
        val length = length()
        x /= length
        y /= length
        z /= length
        return this
    }

    fun crossProduct(v: Vector): Vector {
        val tx = y * v.z - v.y * z
        val ty = z * v.x - v.z * x
        val tz = x * v.y - v.x * y
        set(tx, ty, tz)
        return this
    }

    fun rotateAroundAxis(axis: Vector, angle: Double): Vector {
        val cos = cos(angle)
        val sin = sin(angle)
        val dot = dot(axis)

        val xPrime = axis.x * dot * (1.0 - cos) + x * cos + (-axis.z * y + axis.y * z) * sin
        val yPrime = axis.y * dot * (1.0 - cos) + y * cos + ( axis.z * x - axis.x * z) * sin
        val zPrime = axis.z * dot * (1.0 - cos) + z * cos + (-axis.y * x + axis.x * y) * sin

        this.set(xPrime, yPrime, zPrime)
        return this
    }

}