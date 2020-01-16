package commanderpepper.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable

/**
 * This will be like the player ship except that it's going to be functional
 */
class PlayerShipFnc(
        private val xPosition: Float,
        private val yPosition: Float,
        private val shipHeight: Float,
        private val shipWidth: Float
) : Drawable {
    private val shapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(xPosition, yPosition, shipWidth, shipHeight)
            end()
        }
    }
}
