package commanderpepper.objects

import com.badlogic.gdx.Gdx

data class Point(val xCoordinate: XCoordinate, val yCoordinate: YCoordinate)

data class XCoordinate(val value: Float)

data class YCoordinate(val value: Float)

operator fun YCoordinate.plus(other: YCoordinate) = YCoordinate(value + other.value)

fun checkIfXCoordinateIsTooRight(xCoordinate: XCoordinate, width: Width): Boolean {
    return xCoordinate.value + width.measurement >= Gdx.graphics.width
}

fun checkIfXCoordinateIsTooLeft(xCoordinate: XCoordinate): Boolean {
    return xCoordinate.value <= 0f
}

fun checkIfYCoordianteIsTooHigh(yCoordinate: YCoordinate, height: Height): Boolean {
    val gdxHeight = Gdx.graphics.height.toFloat()
    return yCoordinate.value + height.measurement >= gdxHeight
}

fun Point.increaseYCoordiante(increment: YCoordinate): Point {
    return Point(xCoordinate, this.yCoordinate + increment)
}


