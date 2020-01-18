package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import commanderpepper.*
import commanderpepper.objects.*
import commanderpepper.objects.Player.PlayerShipFnc
import commanderpepper.objects.Player.calculateShipPositionWhenTooLeft
import commanderpepper.objects.Player.calculateShipPositionWhenTooRight
import commanderpepper.objects.Player.checkPlayerShipDirection
import commanderpepper.objects.Player.Fireball.*
import commanderpepper.objects.Player.Fireball.Fireball

class MainScreen : ApplicationAdapter() {

    private lateinit var enemyShipController: EnemyShipController

    private val shipYPosition = 45f
    private var shipXPosition = 25f
    private var shipWidth = 30f
    private val shipHeight = 15f

    private var fireballXDelta = 0f
    private var fireballYDelta = 0f

    private var fireballXPosition = 0f
    private var fireballHeight = 5f
    private var fireballWidth = 5f
    private var fireballOnScreen = false

    override fun create() {
        enemyShipController = EnemyShipController().apply {
            createEnemyShips()
        }
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)
        val shoopInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        shipXPosition += checkPlayerShipDirection(leftInput, rightInput)

        when {
            checkIfXPositionIsTooRight(shipXPosition, shipWidth) -> shipXPosition = calculateShipPositionWhenTooRight(shipWidth)
            checkIfXPositionIsTooLeft(shipXPosition) -> shipXPosition = calculateShipPositionWhenTooLeft()
        }

        val playerShipFnc = PlayerShipFnc(shipXPosition, shipYPosition, shipHeight, shipWidth)
        playerShipFnc.draw()

//        val fireball = Fireball(fireballXPosition, fireballYPosition, fireballHeight, fireballWidth)
        if (shoopInput && !fireballOnScreen) {
            fireballYDelta = playerShipFnc.getFireballYOrigin()
            fireballXPosition = playerShipFnc.getFireballXOrigin()

            val fireball = Fireball(fireballXPosition,
                    fireballYDelta,
                    fireballHeight,
                    fireballWidth)
            fireball.draw()

            fireballOnScreen = true
            fireballYDelta += 1
        }

        if (fireballOnScreen) {
            val fireball = Fireball(fireballXPosition,
                    fireballYDelta,
                    fireballHeight,
                    fireballWidth)
            fireball.draw()

            fireballYDelta += 1
        }

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