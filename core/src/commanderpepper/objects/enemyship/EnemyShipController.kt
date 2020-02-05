package commanderpepper.objects.enemyship

import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate

class EnemyShipController(
        private val leftMargin: XCoordinate,
        private val rightMargin: XCoordinate,
        private val speed: Float) {

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


    fun moveEnemyShips(enemyDirection: EnemyDirection, enemyShipMatrix: List<List<EnemyShip>>): List<List<EnemyShip>> {
        return when (enemyDirection) {
            EnemyDirection.RIGHT -> animateEnemyShipsHorizontally(enemyShipMatrix, calculateRightMovement())
            EnemyDirection.LEFT -> animateEnemyShipsHorizontally(enemyShipMatrix, calculateLeftMovement())
            EnemyDirection.DOWN -> animateEnemyShipsVertically(enemyShipMatrix, calculateDownwardMovement())
            else -> enemyShipMatrix
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