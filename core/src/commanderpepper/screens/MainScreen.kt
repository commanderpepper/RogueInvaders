package commanderpepper.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import commanderpepper.objects.*
import commanderpepper.objects.enemyship.*
import commanderpepper.objects.player.PlayerShip
import commanderpepper.objects.player.calculateShipPositionWhenTooLeft
import commanderpepper.objects.player.calculateShipPositionWhenTooRight
import commanderpepper.objects.player.checkPlayerShipDirection
import commanderpepper.objects.player.fireball.Fireball
import commanderpepper.objects.player.fireball.FireballBar
import commanderpepper.objects.player.fireball.createFireball
import commanderpepper.objects.player.life.InvulnerabilityTime
import commanderpepper.objects.player.life.Life
import commanderpepper.objects.player.score.Score
import java.time.Duration
import java.time.OffsetTime
import java.util.*

class MainScreen(private val game: Game) : Screen {

    private var shipXCoordinate = XCoordinate(50f)
    private var shipYCoordinate = YCoordinate(50f)
    private val shipHeight = Height(15f)
    private val shipWidth = Width(25f)

    private val fireballHeight = Height(8f)
    private val fireballWidth = Width(6f)
    private val fireballYSpeed = YCoordinate(4f)

    private val enemyFireballHeight = Height(6f)
    private val enemyFireballWidth = Width(6f)
    private val enemyFireballYSpeed = YCoordinate(-3.5f)

    private var enemyFireballList: MutableList<Fireball> = mutableListOf()

    private lateinit var fireballPoint: Point

    private val fireballBarXCoordinate = XCoordinate(25f)
    private val fireballBarYCoordinate = YCoordinate(25f)

    private var fireballBarLevel: Int = 0

    private var fireballList = mutableListOf<Fireball>()
    private val fireballChance = 4000000
//    private val fireballChance = 1000000

    private var enemyShipMap = mutableMapOf<Int, EnemyShip>()

    private var speed: Float = .0001f
    private lateinit var enemyShipController: EnemyShipController

    private var enemyDirection = EnemyDirection.RIGHT

    private val scoreXCoordinate = XCoordinate(350f)
    private val scoreYCoordinate = YCoordinate(890f)
    private lateinit var score: Score

    private val lifeXCoordinate = XCoordinate(50f)
    private val lifeYCoordinate = YCoordinate(890f)
    private lateinit var life: Life

    private var invulnerabilityTime = InvulnerabilityTime(Date(Long.MAX_VALUE))

    override fun render(delta: Float) {
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

        enemyFireballList.addAll(
                enemyShipController.createFireballsFromEnemyShips(
                        enemyFireballWidth, enemyFireballHeight, fireballChance, enemyShipMap
                )
        )

        enemyDirection = enemyShipController.checkNextDirection(enemyDirection, enemyShipMap)

        enemyShipController.moveEnemyShips(enemyDirection, enemyShipMap)

        speed += .0001f

        enemyShipController = EnemyShipController(
                XCoordinate(0f),
                XCoordinate(Gdx.graphics.width.toFloat()),
                speed
        )

        enemyShipMap.entries.zip(fireballList).forEach {
            val id = it.first.key
            val enemyShip = it.first.value
            val fireball = it.second
            if(enemyShip.checkForFireballCollision(fireball)){
                enemyShipMap.remove(id)
                fireballList.remove(fireball)
                score = score.increaseScore(3.0)
            }
        }

        for (i in 0 until enemyFireballList.size) {
            if (!invulnerabilityTime.isInvulnerable()) {
                if (playerShip.checkForFireballCollision(enemyFireballList[i])) {
                    enemyFireballList.removeAt(i)
                    life = life.removeLife()
                    invulnerabilityTime = InvulnerabilityTime(Date(System.currentTimeMillis()))
                    break
                }
            }
        }

//        if (life.isGameOver()) {
//            playerShip.dispose()
//            this.dispose()
//            game.screen = GameOverScreen(game)
//        }

        enemyShipMap.values.forEach {
            it.draw()
        }

        fireballList.forEach { fireball ->
            fireball.draw()
        }

        enemyFireballList.forEach {
            it.draw()
        }

        fireballList = fireballList.map { fireball ->
            fireball.createFireBall(fireballYSpeed)
        }.toMutableList()

        enemyFireballList = enemyFireballList.map { fireball ->
            fireball.createFireBall(enemyFireballYSpeed)
        }.toMutableList()

        score.draw()

        life.draw()
    }

    override fun hide() {
        score.dispose()
        Fireball.dispose()
    }

    override fun show() {
        score = Score(scoreXCoordinate, scoreYCoordinate)

        life = Life(lifeXCoordinate, lifeYCoordinate)

        enemyShipMap = createEnemyShipMap(
                3, 8, YCoordinate(20f), XCoordinate(45f), YCoordinate(45f)
        )

        enemyShipController = EnemyShipController(
                XCoordinate(0f),
                XCoordinate(Gdx.graphics.width.toFloat()),
                speed
        )
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
//        score.dispose()

    }
}