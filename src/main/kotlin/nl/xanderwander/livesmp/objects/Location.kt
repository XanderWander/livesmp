package nl.xanderwander.livesmp.objects

import org.bukkit.World
import kotlin.math.floor
import kotlin.math.sqrt

data class Location(
    var x: Double,
    var y: Double,
    var z: Double,
) {

    constructor(v: Vector) : this(v.x, v.y, v.z)
    constructor(location: org.bukkit.Location) : this(location.x, location.y, location.z)

    fun cp(): Location {
        return Location(x, y, z)
    }

    fun toVector(): Vector {
        return Vector(x, y, z)
    }

    fun add(x: Double, y: Double, z: Double): Location {
        this.x += x
        this.y += y
        this.z += z
        return this
    }

    fun add(v: Vector): Location {
        return add(v.x, v.y, v.z)
    }

    fun distance(o: Location): Double {
        return sqrt(distanceSquared(o))
    }

    fun distanceSquared(o: Location): Double {
        return (x-o.x)*(x-o.x) + (y-o.y)*(y-o.y) + (z-o.z)*(z-o.z)
    }

    fun toBukkit(world: World): org.bukkit.Location {
        return org.bukkit.Location(world, x, y, z)
    }

    fun toBlock(): Location {
        return Location(floor(x), floor(y), floor(z))
    }

}