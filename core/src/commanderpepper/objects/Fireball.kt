package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Fireball(var x: Float, var y: Float, var size: Float, var ySpeed: Float) : Moveable{

    override fun move(xDelta: Float, yDelta: Float) {
        y += yDelta
        checkOffScreen()
    }

    private fun checkOffScreen() {
        if (y >= Gdx.graphics.height - size) {
            offScreen = true
        }
    }

    var offScreen = false

    fun update(){
        move(0f, ySpeed)
    }

    fun draw(shape: ShapeRenderer) {
        shape.circle(x, y, size)
    }

    companion object{
        val defaultSize = 10f
        val defaultYSpeed = 15f
    }

}