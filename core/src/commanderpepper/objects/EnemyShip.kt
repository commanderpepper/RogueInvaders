package commanderpepper.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.interfaces.Moveable
import commanderpepper.interfaces.Updatable

class EnemyShip(var x: Float,
                var y: Float,
                var ySpeed: Float,
                var xSpeed: Float,
                val height: Float = 5f,
                val width: Float = 5f) : Moveable, Drawable, Updatable {

    override val shapeRenderer = ShapeRenderer()

    fun updateYSpeed(ySpeedDelta: Float){
        if(ySpeed <= 6){
            ySpeed += ySpeedDelta
        }
    }

    fun updateXSpeed(xSpeedDelta: Float){
        if(xSpeed <= 6){
            xSpeed += xSpeedDelta
        }
    }

    override fun move(xDelta: Float, yDelta: Float) {
        x += xDelta
        y += yDelta
    }

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = Color.GREEN
            rect(x, y, width, height)
            end()
        }
    }

    override fun update() {
        move(xSpeed, 0f)
    }
}