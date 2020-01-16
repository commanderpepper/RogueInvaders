package commanderpepper

import com.badlogic.gdx.Gdx


fun checkPlayerShipDirection(leftInput: Boolean, rightInput: Boolean): Float {
    return when {
        rightInput && leftInput -> 0f
        rightInput -> 3f
        leftInput -> -3f
        else -> 0f
    }
}

fun checkIfXPositionIsTooRight(xPosition: Float, width: Float): Boolean {
    return xPosition + width >= Gdx.graphics.width
}

fun checkIfXPositionIsTooLeft(xPosition: Float): Boolean {
    return xPosition <= 0
}

fun calculateShipPositionWhenTooRight(width: Float) = Gdx.graphics.width - width

fun calculateShipPositionWhenTooLeft() = 0f