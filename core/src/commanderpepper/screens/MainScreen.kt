package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.objects.Fireball
import commanderpepper.objects.PlayerShip

class MainScreen : ApplicationAdapter() {

    private lateinit var shape: ShapeRenderer
    private lateinit var ship: PlayerShip
    private lateinit var fireball: Fireball

    override fun create() {
        shape = ShapeRenderer()
        ship = PlayerShip()
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        shape.begin(ShapeRenderer.ShapeType.Filled)

        ship.update()
        ship.draw(shape)

        ship.playerFireballs.filter { !it.offScreen }.forEach {
            it.update()
            it.draw(shape)
        }

        shape.end()

    }
}