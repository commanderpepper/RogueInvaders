package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Fireball(var x: Float, var y: Float, var size: Float, var ySpeed: Float) {

    var offScreen = false

    fun update(){
        move()
    }

    private fun move() {
        y += ySpeed
        if (y >= Gdx.graphics.height - size) {
            offScreen = true
        }
    }

    fun draw(shape: ShapeRenderer) {
        shape.circle(x, y, size)
    }

    companion object{
        val defaultSize = 10f
        val defaultYSpeed = 15f
    }

}