package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width
import commanderpepper.objects.baseshapes.Rectangle

enum class FireballLevel(private val range: IntRange) {
    Low(0..5),
    Medium(6..10),
    High(11..15)
}

