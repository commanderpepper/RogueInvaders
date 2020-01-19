package commanderpepper.objects

import com.badlogic.gdx.Gdx

data class Point(val xCoordinate: XCoordinate, val yCoordinate: YCoordinate)

data class XCoordinate(val value: Float)

data class YCoordinate(val value: Float)

fun checkIfXCoordinateIsTooRight(xCoordinate: XCoordinate, width: Width): Boolean {
    return xCoordinate.value + width.measurement >= Gdx.graphics.width
}

fun checkIfXCoordinateIsTooLeft(xCoordinate: XCoordinate): Boolean {
    return xCoordinate.value <= 0f
}

