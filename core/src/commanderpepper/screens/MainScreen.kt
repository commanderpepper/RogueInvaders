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

//    private val shipYPosition = 45f
//    private var shipXPosition = 25f
//    private var shipWidth = 30f
//    private val shipHeight = 15f
//
//    private var fireballXDelta = 0f
//    private var fireballYDelta = 0f
//
//    private var fireballXPosition = 0f
//    private var fireballHeight = 5f
//    private var fireballWidth = 5f
//    private var fireballOnScreen = false

    private var shipXCoordinate = XCoordinate(45f)
    private var shipYCoordinate = YCoordinate(25f)

    private val shipHeight = Height(15f)
    private val shipWidth = Width(25f)

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

        val playerShipFnc = PlayerShipFnc(shipPoint, shipHeight, shipWidth)
        playerShipFnc.draw()

//        val fireball = Fireball(fireballXPosition, fireballYPosition, fireballHeight, fireballWidth)
//        if (shoopInput && !fireballOnScreen) {
//            fireballYDelta = playerShipFnc.getFireballYOrigin()
//            fireballXPosition = playerShipFnc.getFireballXOrigin()
//
//            val fireball = Fireball(fireballXPosition,
//                    fireballYDelta,
//                    fireballHeight,
//                    fireballWidth)
//            fireball.draw()
//
//            fireballOnScreen = true
//            fireballYDelta += 1
//        }
//
//        if (fireballOnScreen) {
//            val fireball = Fireball(fireballXPosition,
//                    fireballYDelta,
//                    fireballHeight,
//                    fireballWidth)
//            fireball.draw()
//
//            fireballYDelta += 1
//        }
    }


}