package commanderpepper.objects.Player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.BaseShapes.Rectangle

/**
 * This will be like the player ship except that it's going to be functional
 */
class PlayerShipFnc(
        private val shipXPosition: Float,
        private val shipYPosition: Float,
        private val shipHeight: Float,
        private val shipWidth: Float
) : Rectangle(xPosition = shipXPosition, yPosition = shipYPosition, height = shipHeight, width = shipWidth), Drawable {

    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(shipXPosition, shipYPosition, shipWidth, shipHeight)
            end()
        }
    }

    fun getFireballXOrigin(): Float {
        return shipXPosition + shipWidth / 2
    }

    fun getFireballYOrigin(): Float {
        return shipYPosition + shipHeight
    }

}

fun calculateShipPositionWhenTooRight(width: Float) = Gdx.graphics.width - width

fun calculateShipPositionWhenTooLeft(leftLimit: Float = 0f) = leftLimit

fun checkPlayerShipDirection(leftInput: Boolean, rightInput: Boolean): Float {
    return when {
        rightInput && leftInput -> 0f
        rightInput -> 3f
        leftInput -> -3f
        else -> 0f
    }
}
