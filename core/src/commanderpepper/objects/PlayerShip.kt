package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class PlayerShip(var x: Float = 25f,
                 val y: Float = 25f,
                 val height: Float = 15f,
                 val width: Float = 45f) {


    fun middleOfShip() = x + ((x) / 2)

//    var middleOfShip = (x + width) / 2

    val playerFireballs = mutableListOf<Fireball>()

    fun draw(shapeRenderer: ShapeRenderer) {
        shapeRenderer.rect(x, y, width, height)
    }

    fun update() {
        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)
        val shootInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        when {
            rightInput -> x += 3f
            leftInput -> x -= 3f
        }

        if(shootInput) playerFireballs.add(shoot())
    }

    /**
     * Send a fireball from the middle of the ship
     */
    fun shoot(): Fireball {
        return Fireball(middleOfShip(), y, Fireball.defaultSize, Fireball.defaultYSpeed)
    }

}