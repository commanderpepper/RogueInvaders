package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width
import commanderpepper.objects.baseshapes.Rectangle

class FireballBar(
        private val fireballLevel: FireballLevel,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {
    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}