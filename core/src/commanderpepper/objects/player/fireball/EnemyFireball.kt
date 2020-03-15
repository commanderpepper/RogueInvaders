package commanderpepper.objects.player.fireball

import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width
import commanderpepper.objects.baseshapes.Rectangle

class EnemyFireball (
        private val level: Int = 0,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(
        point, height, width
), Drawable{

    override fun draw() {

    }

}