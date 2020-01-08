package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.objects.EnemyShip
import commanderpepper.objects.Fireball
import commanderpepper.objects.PlayerShip

class MainScreen : ApplicationAdapter() {

    private lateinit var ship: PlayerShip
    private lateinit var enemyShip: EnemyShip

    override fun create() {
        ship = PlayerShip()
        enemyShip = EnemyShip(200f, 200f, 1f, 1f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        ship.update()
        ship.draw()

        ship.playerFireballs = ship.playerFireballs.filter { !it.offScreen }.toMutableList()
        ship.playerFireballs.forEach {
            it.update()
            it.draw()
        }

//        enemyShip.updateYSpeed(1f)
        enemyShip.updateXSpeed(1f)
        enemyShip.update()
        enemyShip.draw()

    }
}