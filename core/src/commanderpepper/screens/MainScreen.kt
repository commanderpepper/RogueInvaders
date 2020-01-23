package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import commanderpepper.objects.*
import commanderpepper.objects.player.PlayerShip
import commanderpepper.objects.player.calculateShipPositionWhenTooLeft
import commanderpepper.objects.player.calculateShipPositionWhenTooRight
import commanderpepper.objects.player.checkPlayerShipDirection
import commanderpepper.objects.player.fireball.Fireball

class MainScreen : ApplicationAdapter() {

    private var shipXCoordinate = XCoordinate(45f)
    private var shipYCoordinate = YCoordinate(25f)
    private val shipHeight = Height(15f)
    private val shipWidth = Width(25f)

    private val fireballHeight = Height(8f)
    private val fireballWidth = Width(8f)
    private val fireballYSpeed = YCoordinate(4f)

    private var fireBallOnScreen = false
    private lateinit var fireballPoint: Point

    override fun create() {

    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val rightInput = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyPressed(Input.Keys.LEFT)
        val shoopInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        shipXCoordinate = checkPlayerShipDirection(shipXCoordinate, leftInput, rightInput)

        /**
         * This allows for player ship to wrap the screen
         * Cool but, not intended. Might add it in the future.
         */
//        shipXCoordinate = when {
//            checkIfXCoordinateIsTooLeft(shipXCoordinate) -> calculateShipPositionWhenTooRight(shipWidth)
//            checkIfXCoordinateIsTooRight(shipXCoordinate, shipWidth) -> calculateShipPositionWhenTooLeft()
//            else -> shipXCoordinate
//        }

        shipXCoordinate = when {
            checkIfXCoordinateIsTooLeft(shipXCoordinate) -> calculateShipPositionWhenTooLeft()
            checkIfXCoordinateIsTooRight(shipXCoordinate, shipWidth) -> calculateShipPositionWhenTooRight(shipWidth)
            else -> shipXCoordinate
        }

        val shipPoint = Point(shipXCoordinate, shipYCoordinate)

        val playerShip = PlayerShip(shipPoint, shipHeight, shipWidth)
        playerShip.draw()

        if (shoopInput && !fireBallOnScreen) {
            fireballPoint = playerShip.getFireballPointOrigin(fireballWidth)
            val fireball = Fireball(fireballPoint, fireballHeight, fireballWidth)
            fireball.draw()
            fireBallOnScreen = true
        }

        if (fireBallOnScreen) {
            if (!checkIfYCoordianteIsTooHigh(fireballPoint.yCoordinate, fireballHeight)) {
                fireballPoint = fireballPoint.increaseYCoordiante(fireballYSpeed)
                val fireball = Fireball(fireballPoint, fireballHeight, fireballWidth)
                fireball.draw()
            } else {
                fireBallOnScreen = false
            }
        }
    }


}