package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Point
import commanderpepper.objects.Width
import commanderpepper.objects.YCoordinate
import commanderpepper.objects.baseshapes.Rectangle

class FireballBar private constructor(
        private val barColor: Color,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {
    override val shapeRenderer: ShapeRenderer = ShapeRenderer()

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = barColor
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    companion object {
        fun createFireballBar(level: Int, point: Point, height: Height): FireballBar {
            return FireballBar((::determineFireballBarColor)(level), point, height, (::determineFireballBarWidth)(level))
        }
    }
}

private val low = 0..60
private val medium = 61..120

fun determineFireballBarColor(level: Int): Color {
    return when (level) {
        in low -> Color.YELLOW
        in medium -> Color.ORANGE
        else -> Color.RED
    }
}

fun determineFireballBarWidth(level: Int): Width {
    return when (level) {
        in low -> Width(30f)
        in medium -> Width(60f)
        else -> Width(120f)
    }
}