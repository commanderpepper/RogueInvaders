package commanderpepper.objects

import com.badlogic.gdx.Gdx
import commanderpepper.interfaces.Drawable
import commanderpepper.interfaces.Updatable
import kotlin.math.abs

private const val horizontalEnemyShipDistance = 35
private const val verticalEnemyShipDistance = 45

class EnemyShipController() : Drawable, Updatable {

    private val enemyShipList = mutableListOf<EnemyShip>()
    private var enemyShipHorizontalSpeed = 1f

    private var leftMostEnemyShipPosition = horizontalEnemyShipDistance.toFloat()
    private var rightMostEnemyShipPosition = (Gdx.graphics.width - verticalEnemyShipDistance).toFloat()

    private var leftEnemyShipLimit = horizontalEnemyShipDistance.toFloat()
    private var rightEnemyShipLimit = (Gdx.graphics.width - 5).toFloat()

    override fun draw() {
        enemyShipList.forEach {
            it.draw()
        }
    }

    override fun update() {
        enemyShipHorizontalSpeed = determineHorizontalSpeed(enemyShipHorizontalSpeed)
        if (enemyShipHorizontalSpeed > 0) {
            rightMostEnemyShipPosition += enemyShipHorizontalSpeed
        } else {
            leftMostEnemyShipPosition += enemyShipHorizontalSpeed
        }

        enemyShipList.forEach {
            it.xSpeed = enemyShipHorizontalSpeed
            it.update()
        }
    }

    fun createEnemyShips() {
        for (row in 1..3) {
            for (column in 0..9) {
                EnemyShip(horizontalEnemyShipDistance + column.toFloat() * horizontalEnemyShipDistance,
                        Gdx.graphics.height - row.toFloat() * verticalEnemyShipDistance,
                        0f,
                        0f).also {
                    enemyShipList.add(it)
                }
            }
        }
    }

    fun determineHorizontalSpeed(horizontalSpeed: Float): Float {
        var newSpeed = horizontalSpeed
        //If true start going right
        if (leftMostEnemyShipPosition == leftEnemyShipLimit) {
            newSpeed = abs(horizontalSpeed)
        }
        //If true, start going left
        if (rightMostEnemyShipPosition == rightEnemyShipLimit) {
            newSpeed = horizontalSpeed * -1
        }
        return newSpeed
    }
}