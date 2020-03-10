package commanderpepper.objects.enemyship

import commanderpepper.objects.*
import commanderpepper.objects.player.fireball.Fireball
import kotlin.random.Random

class EnemyShipController(
        private val leftMargin: XCoordinate,
        private val rightMargin: XCoordinate,
        private val speed: Float) {

    private val enemyShipMap: MutableMap<Int, EnemyShip> = mutableMapOf()

    private fun calculateDownwardMovement(): YCoordinate {
        return YCoordinate(speed * -1)
    }

    private fun calculateLeftMovement(): XCoordinate {
        return XCoordinate(speed * -1)
    }

    private fun calculateRightMovement(): XCoordinate {
        return XCoordinate(speed)
    }

    fun checkNextDirection(enemyDirection: EnemyDirection, enemyShipMatrix: List<List<EnemyShip>>): EnemyDirection {
        val leftMostShips = getShipsUsingIndex(enemyShipMatrix) { it == 0 }

        val rightMostShips = rightMostShips(enemyShipMatrix)

        val isTooLeft = leftMostShips.map {
            it.moveShip(XCoordinate(speed * -1))
        }.any {
            it.checkIfEnemyShipIsTooLeft()
        }

        val isTooRight = rightMostShips.map {
            it.moveShip(XCoordinate(speed))
        }.any {
            it.checkIfEnemyShipIsTooRight()
        }

        if (isTooLeft && enemyDirection == EnemyDirection.DOWN) {
            return EnemyDirection.RIGHT
        }

        if (isTooRight && enemyDirection == EnemyDirection.DOWN) {
            return EnemyDirection.LEFT
        }

        if (isTooLeft || isTooRight) {
            return EnemyDirection.DOWN
        }

        return enemyDirection
    }

    fun checkNextDirection(enemyDirection: EnemyDirection, enemyShipMap: Map<Int, EnemyShip>): EnemyDirection {
        val ships = enemyShipMap.values

        val isTooLeft = ships.map {
            it.moveShip(XCoordinate(speed * -1))
        }.any {
            it.checkIfEnemyShipIsTooLeft()
        }

        val isTooRight = ships.map {
            it.moveShip(XCoordinate(speed))
        }.any {
            it.checkIfEnemyShipIsTooRight()
        }

        if (isTooLeft && enemyDirection == EnemyDirection.DOWN) {
            return EnemyDirection.RIGHT
        }

        if (isTooRight && enemyDirection == EnemyDirection.DOWN) {
            return EnemyDirection.LEFT
        }

        if (isTooLeft || isTooRight) {
            return EnemyDirection.DOWN
        }

        return enemyDirection
    }

    fun moveEnemyShips(enemyDirection: EnemyDirection, enemyShipMatrix: List<List<EnemyShip>>): List<List<EnemyShip>> {
        return when (enemyDirection) {
            EnemyDirection.RIGHT -> animateEnemyShipsHorizontally(enemyShipMatrix, calculateRightMovement())
            EnemyDirection.LEFT -> animateEnemyShipsHorizontally(enemyShipMatrix, calculateLeftMovement())
            EnemyDirection.DOWN -> animateEnemyShipsVertically(enemyShipMatrix, calculateDownwardMovement())
            else -> enemyShipMatrix
        }
    }

    fun moveEnemyShips(enemyDirection: EnemyDirection, enemyShipMap: MutableMap<Int, EnemyShip>) {
        when (enemyDirection) {
            EnemyDirection.RIGHT -> {
                moveShipsHorizontally(enemyShipMap, calculateRightMovement())
            }
            EnemyDirection.LEFT -> {
                moveShipsHorizontally(enemyShipMap, calculateLeftMovement())
            }
            EnemyDirection.DOWN -> {
                moveShipsVertically(enemyShipMap, calculateDownwardMovement())
            }
        }
    }

    private fun rightMostShips(enemyShipMatrix: List<List<EnemyShip>>): MutableList<EnemyShip> {
        val ships = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            ships.addAll(row.filterIndexed { index, _ ->
                index == row.size - 1
            })
        }
        return ships
    }

    private fun getShipsUsingIndex(enemyShipMatrix: List<List<EnemyShip>>,
                                   predicate: (Int) -> Boolean): MutableList<EnemyShip> {
        val ships = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            ships.addAll(row.filterIndexed { index, _ ->
                predicate(index)
            })
        }
        return ships
    }

    fun createFireballsFromEnemyShips(fireballWidth: Width, fireballHeight: Height, randomChance: Int, enemyShipMatrix: List<List<EnemyShip>>): List<Fireball> {
        val enemyShips = enemyShipMatrix.flatten()
        val fireBallList = mutableListOf<Fireball>()

        enemyShips.forEach {
            val enemyNumber = Random.nextInt(0, Int.MAX_VALUE)
            val intMax = Int.MAX_VALUE
            val result = intMax - enemyNumber
            if (result <= randomChance) {
                fireBallList.add(Fireball(0, it.getFireballPointOrigin(fireballWidth), fireballHeight, fireballWidth))
            }
        }

        return fireBallList
    }

    fun createFireballsFromEnemyShips(fireballWidth: Width, fireballHeight: Height, randomChance: Int, enemyShipMap: Map<Int, EnemyShip>): List<Fireball> {
        val enemyShips = enemyShipMap.values
        val fireBallList = mutableListOf<Fireball>()

        enemyShips.forEach {
            val enemyNumber = Random.nextInt(0, Int.MAX_VALUE)
            val intMax = Int.MAX_VALUE
            val result = intMax - enemyNumber
            if (result <= randomChance) {
                fireBallList.add(Fireball(0, it.getFireballPointOrigin(fireballWidth), fireballHeight, fireballWidth))
            }
        }

        return fireBallList
    }

    fun moveShipsHorizontally(enemyShipMap: MutableMap<Int, EnemyShip>, xCoordinate: XCoordinate) {
        enemyShipMap.keys.forEach {
            enemyShipMap[it] = enemyShipMap[it]!!.moveShip(xCoordinate)
        }
    }

    fun moveShipsVertically(enemyShipMap: MutableMap<Int, EnemyShip>, yCoordinate: YCoordinate) {
        enemyShipMap.keys.forEach {
            enemyShipMap[it] = enemyShipMap[it]!!.moveShip(yCoordinate)
        }
    }

    companion object {

        private fun animateEnemyShipsHorizontally(enemyShipMatrix: List<List<EnemyShip>>, xCoordinate: XCoordinate): List<List<EnemyShip>> {
            return enemyShipMatrix.map {
                it.map { enemyShip ->
                    enemyShip.moveShip(xCoordinate)
                }
            }
        }

        private fun animateEnemyShipsVertically(enemyShipMatrix: List<List<EnemyShip>>, yCoordinate: YCoordinate): List<List<EnemyShip>> {
            return enemyShipMatrix.map {
                it.map { enemyShip ->
                    enemyShip.moveShip(yCoordinate)
                }
            }
        }
    }
}

sealed class Movement() {
    object Horizontally : Movement()
    object Vertically : Movement()
    object Circular : Movement()
}

enum class EnemyDirection() {
    UP,
    DOWN,
    RIGHT,
    LEFT
}

fun oppositeDirection(enemyDirection: EnemyDirection): EnemyDirection {
    return when (enemyDirection) {
        EnemyDirection.UP -> EnemyDirection.DOWN
        EnemyDirection.DOWN -> EnemyDirection.UP
        EnemyDirection.LEFT -> EnemyDirection.RIGHT
        EnemyDirection.RIGHT -> EnemyDirection.LEFT
    }
}