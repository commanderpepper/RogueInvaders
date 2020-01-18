package commanderpepper

import com.badlogic.gdx.Gdx




fun checkIfXPositionIsTooRight(xPosition: Float, width: Float): Boolean {
    return xPosition + width >= Gdx.graphics.width
}

fun checkIfXPositionIsTooLeft(xPosition: Float): Boolean {
    return xPosition <= 0
}

