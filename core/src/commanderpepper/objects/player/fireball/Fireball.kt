package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.baseshapes.Rectangle
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width

class Fireball(
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(
        point, height, width
), Drawable {

    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.LIGHT_GRAY
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }
}
