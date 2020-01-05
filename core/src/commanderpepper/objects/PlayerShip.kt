package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class PlayerShip(var x: Float = 25f,
                 var y: Float = 25f,
                 val height: Float = 15f,
                 val width: Float = 45f) : Moveable {

    override fun move(xDelta: Float, yDelta: Float) {
        x += xDelta
        y += yDelta
    }

    fun middleOfShip() = x + (width / 2)

    val playerFireballs = mutableListOf<Fireball>()

    fun draw(shapeRenderer: ShapeRenderer) {
        shapeRenderer.rect(x, y, width, height)
    }

    fun update() {
        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)
        val shootInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        when {
            rightInput -> move(3f, 0f)
            leftInput -> move(-3f, 0f)
        }

        if (shootInput) playerFireballs.add(shoot())
    }

    /**
     * Send a fireball from the middle of the ship
     */
    fun shoot(): Fireball {
        return Fireball(middleOfShip(), y, Fireball.defaultSize, Fireball.defaultYSpeed)
    }

}