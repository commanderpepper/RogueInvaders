package commanderpepper.objects.Player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.BaseShapes.Rectangle

/**
 * This will be like the player ship except that it's going to be functional
 */
class PlayerShipFnc(
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {

    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    private fun getFireballXOrigin(): XCoordinate {
        return XCoordinate(point.xCoordinate.value + (width.measurement / 2))
    }

    private fun getFireballYOrigin(): YCoordinate {
        return YCoordinate(point.yCoordinate.value + height.measurement)
    }

    fun getFireballPointOrigin(): Point {
        return Point(getFireballXOrigin(), getFireballYOrigin())
    }

}

fun calculateShipPositionWhenTooRight(width: Width) = XCoordinate(Gdx.graphics.width - width.measurement)

fun calculateShipPositionWhenTooLeft(xCoordinateLimit: XCoordinate = XCoordinate(0f)) = xCoordinateLimit

fun checkPlayerShipDirection(xCoordinate: XCoordinate, leftInput: Boolean, rightInput: Boolean): XCoordinate {
    return when {
        rightInput && leftInput -> xCoordinate.copy()
        rightInput -> XCoordinate(xCoordinate.value + 3f)
        leftInput -> XCoordinate(xCoordinate.value - 3f)
        else -> xCoordinate.copy()
    }
}
