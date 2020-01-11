package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.objects.EnemyShip
import commanderpepper.objects.EnemyShipController
import commanderpepper.objects.Fireball
import commanderpepper.objects.PlayerShip

class MainScreen : ApplicationAdapter() {

    private lateinit var ship: PlayerShip
    private lateinit var enemyShipController: EnemyShipController

    private var shipXPosition = 25f
    private var shipWidth = 30f

    override fun create() {
        ship = PlayerShip()
        enemyShipController = EnemyShipController().apply {
            createEnemyShips()
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)

        shipXPosition += checkPlayerShipDirection(leftInput, rightInput)

        if (checkIfXPositionIsOnScreen(shipXPosition, shipWidth)) {
            when {
                checkIfXPositionIsTooRight(shipXPosition, shipWidth) -> shipXPosition = Gdx.graphics.width - shipWidth
                checkIfXPositionIsTooLeft(shipXPosition) -> shipXPosition = 0f
            }
        }

        val playerShipFnc = PlayerShip(shipXPosition, 25f, 15f, shipWidth)
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

    fun checkPlayerShipDirection(leftInput: Boolean, rightInput: Boolean): Float {
        return when {
            rightInput && leftInput -> 0f
            rightInput -> 3f
            leftInput -> -3f
            else -> 0f
        }
    }

    fun checkIfXPositionIsOnScreen(xPosition: Float, width: Float): Boolean {
        return when {
            checkIfXPositionIsTooRight(xPosition, width) -> true
            checkIfXPositionIsTooLeft(xPosition) -> true
            else -> false
        }
    }

    fun checkIfXPositionIsTooRight(xPosition: Float, width: Float): Boolean {
        return xPosition + width >= Gdx.graphics.width
    }

    fun checkIfXPositionIsTooLeft(xPosition: Float): Boolean {
        return xPosition <= 0
    }

}