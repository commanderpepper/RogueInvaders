package commanderpepper.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Moveable

class PlayerShip(var x: Float = 25f,
                 var y: Float = 25f,
                 val height: Float = 15f,
                 val width: Float = 30f) : Moveable {

    private val shapeRenderer = ShapeRenderer()

    override fun move(xDelta: Float, yDelta: Float) {
        when {
            x + xDelta < 0 -> x = 0f
            x + width + xDelta > Gdx.graphics.width -> x = Gdx.graphics.width - width
            else -> x += xDelta
        }
        y += yDelta
    }

    private fun middleOfShip() = x + (width / 2)

    var playerFireballs = mutableListOf<Fireball>()

    fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            rect(x, y, width, height)
            end()
        }
    }

    fun update() {
        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)
        val shootInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        when {
            rightInput -> move(3f, 0f)
            leftInput -> move(-3f, 0f)
        }

        if (shootInput && playerFireballs.isEmpty()) playerFireballs.add(shoot())
    }

    /**
     * Send a fireball from the middle of the ship
     */
    private fun shoot(): Fireball {
        return Fireball(middleOfShip(), y, Fireball.defaultSize, Fireball.defaultYSpeed)
    }

}