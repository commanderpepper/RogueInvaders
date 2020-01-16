package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import commanderpepper.*
import commanderpepper.objects.*

class MainScreen : ApplicationAdapter() {

    private lateinit var enemyShipController: EnemyShipController

    private var shipXPosition = 25f
    private var shipWidth = 30f

    override fun create() {
        enemyShipController = EnemyShipController().apply {
            createEnemyShips()
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)

        shipXPosition += checkPlayerShipDirection(leftInput, rightInput)

        when {
            checkIfXPositionIsTooRight(shipXPosition, shipWidth) -> shipXPosition = calculateShipPositionWhenTooRight(shipWidth)
            checkIfXPositionIsTooLeft(shipXPosition) -> shipXPosition = calculateShipPositionWhenTooLeft()
        }

        val playerShipFnc = PlayerShipFnc(shipXPosition, 25f, 15f, shipWidth)
        playerShipFnc.draw()

//        ship.update()
//        ship.draw()
//
//        ship.playerFireballs = ship.playerFireballs.filter { !it.offScreen }.toMutableList()
//        ship.playerFireballs.forEach {
//            it.update()
//            it.draw()
//        }
//
//        enemyShipController.update()
//        enemyShipController.draw()
    }


}