package commanderpepper

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.Height
import commanderpepper.objects.Width
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate
import commanderpepper.objects.enemyship.EnemyShip

class Cursor(private val cursorColor: Color,
             private val xCoordinate: XCoordinate,
             private val yCoordinate: YCoordinate,
             private val height: Height,
             private val width: Width) : Drawable {

    override fun draw() {
        EnemyShip.shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = cursorColor
            triangle(xCoordinate.value,
                    yCoordinate.value,
                    xCoordinate.value,
                    yCoordinate.value + height.measurement,
                    xCoordinate.value + width.measurement,
                    yCoordinate.value + height.measurement / 2)
            end()
        }
    }

    fun dispose() {
        EnemyShip.shapeRenderer.dispose()
    }

    companion object {
        val shapeRenderer = ShapeRenderer()
    }
}
