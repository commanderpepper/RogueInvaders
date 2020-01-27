package commanderpepper.objects.player.fireball

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle

class Fireball(
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(
        point, height, width
), Drawable {

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    fun checkIfFireballYCoordinateIsTooHigh(): Boolean {
        val gdxHeight = Gdx.graphics.height.toFloat()
        return point.yCoordinate.value + height.measurement >= gdxHeight
    }

    fun createFireBall(fireballSpeed: YCoordinate): Fireball {
        return Fireball(point.increaseYCoordiante(fireballSpeed), height, width)
    }

    companion object {
        private val shapeRenderer = ShapeRenderer()
    }
}
