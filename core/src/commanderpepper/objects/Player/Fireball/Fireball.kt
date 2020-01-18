package commanderpepper.objects.Player.Fireball

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.BaseShapes.Rectangle

class Fireball(
        private val fireballXPosition: Float,
        private val fireballYPosition: Float,
        private val fireballHeight: Float,
        private val fireballWidth: Float
) : Rectangle(
        fireballXPosition, fireballYPosition, fireballHeight, fireballWidth
), Drawable {

    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(fireballXPosition, fireballYPosition, fireballWidth, fireballHeight)
            end()
        }
    }
}
