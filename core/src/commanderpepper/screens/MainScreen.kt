package commanderpepper.screens

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import commanderpepper.objects.*
import commanderpepper.objects.enemyship.EnemyDirection
import commanderpepper.objects.enemyship.EnemyShip
import commanderpepper.objects.enemyship.EnemyShipController
import commanderpepper.objects.enemyship.createEnemyShipMatrix
import commanderpepper.objects.player.PlayerShip
import commanderpepper.objects.player.calculateShipPositionWhenTooLeft
import commanderpepper.objects.player.calculateShipPositionWhenTooRight
import commanderpepper.objects.player.checkPlayerShipDirection
import commanderpepper.objects.player.fireball.Fireball
import commanderpepper.objects.player.fireball.FireballBar
import commanderpepper.objects.player.fireball.createFireball
import commanderpepper.objects.player.score.Score

class MainScreen : ApplicationAdapter() {

    private var shipXCoordinate = XCoordinate(50f)
    private var shipYCoordinate = YCoordinate(50f)
    private val shipHeight = Height(15f)
    private val shipWidth = Width(25f)

    private val fireballHeight = Height(8f)
    private val fireballWidth = Width(6f)
    private val fireballYSpeed = YCoordinate(4f)

    private lateinit var fireballPoint: Point

    private val fireballBarXCoordinate = XCoordinate(25f)
    private val fireballBarYCoordinate = YCoordinate(25f)

    private var fireballBarLevel: Int = 0

    private var fireballList = mutableListOf<Fireball>()

    private lateinit var enemyShipList: List<MutableList<EnemyShip>>

    private var speed: Float = .00001f
    private lateinit var enemyShipController: EnemyShipController

    private var enemyDirection = EnemyDirection.RIGHT

    private val scoreXCoordinate = XCoordinate(350f)
    private val scoreYCoordinate = YCoordinate(890f)
    private lateinit var score: Score

    override fun create() {

        score = Score(scoreXCoordinate, scoreYCoordinate)

        enemyShipList = createEnemyShipMatrix(
                3,
                8,
                YCoordinate(20f),
                XCoordinate(45f),
                YCoordinate(45f)).map {
            it.toMutableList()
        }
        enemyShipController = EnemyShipController(
                XCoordinate(0f),
                XCoordinate(Gdx.graphics.width.toFloat()),
                speed
        )
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

        fireballBarLevel++
        val fireballBar = FireballBar.createFireballBar(fireballBarLevel,
                Point(fireballBarXCoordinate, fireballBarYCoordinate), Height(15f))
        fireballBar.draw()

        if (shoopInput) {
            fireballPoint = playerShip.getFireballPointOrigin(fireballWidth)
            val fireball = createFireball(fireballBarLevel, fireballPoint, fireballHeight, fireballWidth)
            fireballList.add(fireball)
            fireballBarLevel = 0
        }

        fireballList.removeAll { fireball ->
            fireball.checkIfFireballYCoordinateIsTooHigh()
        }

        enemyDirection = enemyShipController.checkNextDirection(enemyDirection, enemyShipList)

        enemyShipList = enemyShipController.moveEnemyShips(enemyDirection, enemyShipList).map { it.toMutableList() }

        speed += .0001f

        enemyShipController = EnemyShipController(
                XCoordinate(0f),
                XCoordinate(Gdx.graphics.width.toFloat()),
                speed
        )

        enemyShipList.forEachIndexed { rowIndex, shipRow ->
            shipRow.zip(fireballList).forEachIndexed { columnIndex, pair ->
                if (pair.first.checkForFireballCollision(pair.second)) {
                    enemyShipList[rowIndex].removeAt(columnIndex)
                    fireballList.remove(pair.second)
                    score = score.increaseScore(3.0)
                }
            }
        }

        enemyShipList.flatten().forEach {
            it.draw()
        }

        fireballList.forEach { fireball ->
            fireball.draw()
        }

        fireballList = fireballList.map { fireball ->
            fireball.createFireBall(fireballYSpeed)
        }.toMutableList()

        score.draw()
    }
}