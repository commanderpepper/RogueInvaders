package commanderpepper.objects

import com.badlogic.gdx.Gdx

data class Point(val xCoordinate: XCoordinate, val yCoordinate: YCoordinate)

data class XCoordinate(val value: Float)

data class YCoordinate(val value: Float)

operator fun YCoordinate.plus(other: YCoordinate) = YCoordinate(value + other.value)

operator fun YCoordinate.minus(other: YCoordinate) = YCoordinate(value - other.value)

operator fun XCoordinate.plus(other: XCoordinate) = XCoordinate(value + other.value)

operator fun XCoordinate.minus(other: XCoordinate) = XCoordinate(value - other.value)

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

fun Point.decreaseYCoordiante(decrement: YCoordinate): Point {
    return Point(xCoordinate, this.yCoordinate - decrement)
}

fun Point.increaseXCoordiante(increment: XCoordinate): Point {
    return Point(xCoordinate + increment, this.yCoordinate)
}

fun Point.decreaseXCoordiante(decrement: XCoordinate): Point {
    return Point(xCoordinate - decrement, this.yCoordinate)
}

fun Point.isInContact(thisHeight: Height, otherPoint: Point, otherHeight: Height, leeway: Float = 16f): Boolean {

    val thisPointYValue = this.yCoordinate.value
    val otherPointYValue = otherPoint.yCoordinate.value

    val thisPointHeight = thisHeight.measurement - leeway * 2
    val otherPointHeight = otherHeight.measurement - leeway * 2

    val thisHighYValue = thisPointYValue + thisPointHeight + leeway * 3
    val otherHighYValue = otherPointYValue + otherPointHeight + leeway * 3

    return thisPointYValue in otherPointYValue..otherHighYValue || otherHighYValue in thisPointYValue..thisHighYValue

}

fun Point.isInContact(thisWidth: Width, otherPoint: Point, otherWidth: Width, leeway: Float = 16f): Boolean {

    val thisPointXValue = this.xCoordinate.value
    val otherPointXValue = otherPoint.xCoordinate.value

    val thisPointWidth = thisWidth.measurement - leeway * 2
    val otherPointWidth = otherWidth.measurement - leeway * 2

    val thisHighXValue = thisPointXValue + thisPointWidth + leeway * 3
    val otherHighXValue = otherPointXValue + otherPointWidth + leeway * 3

    return thisPointXValue in otherPointXValue..otherHighXValue || otherHighXValue in thisPointXValue..thisHighXValue

}



