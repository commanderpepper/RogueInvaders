package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Fireball(var x: Float, var y: Float, var size: Float, var ySpeed: Float) : Moveable {

    private val shapeRenderer = ShapeRenderer()
    var offScreen = false

    override fun move(xDelta: Float, yDelta: Float) {
        y += yDelta
        checkOffScreen()
    }

    private fun checkOffScreen() {
        if (y >= Gdx.graphics.height - size) {
            offScreen = true
        }
    }

    fun update() {
        move(0f, ySpeed)
    }

    fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            circle(x, y, size)
            end()
        }
    }

    companion object {
        const val defaultSize = 6f
        const val defaultYSpeed = 15f
    }

}