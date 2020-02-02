package commanderpepper.objects.enemyship

import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate

class EnemyShipController(
        private val leftMargin: XCoordinate,
        private val rightMargin: XCoordinate,
        private val speed: Float) {

    fun checkNextDirection(enemyDirection: EnemyDirection, enemyShipMatrix: List<List<EnemyShip>>): EnemyDirection {
        val leftMostShips = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            leftMostShips.addAll(row.filterIndexed { index, _ ->
                index == 0
            })
        }

        val rightMostShips = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            rightMostShips.addAll(row.filterIndexed { index, _ ->
                index == row.size - 1
            })
        }

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
        val leftMostShips = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            leftMostShips.addAll(row.filterIndexed { index, _ ->
                index == 0
            })
        }

        val rightMostShips = mutableListOf<EnemyShip>()
        enemyShipMatrix.forEach { row ->
            rightMostShips.addAll(row.filterIndexed { index, _ ->
                index == row.size - 1
            })
        }

        if (enemyDirection == EnemyDirection.RIGHT) {
            return animateEnemyShipsHorizontally(enemyShipMatrix, 2f)
        }

        if (enemyDirection == EnemyDirection.LEFT) {
            return animateEnemyShipsHorizontally(enemyShipMatrix, -2f)
        }

        if (enemyDirection == EnemyDirection.DOWN) {
            return animateEnemyShipsVertically(enemyShipMatrix, -6f)
        }

        return enemyShipMatrix

    }

    companion object {
        
        private fun animateEnemyShipsHorizontally(enemyShipMatrix: List<List<EnemyShip>>, speed: Float): List<List<EnemyShip>> {
            return enemyShipMatrix.map {
                it.map { enemyShip ->
                    enemyShip.moveShip(XCoordinate(speed))
                }
            }
        }

        private fun animateEnemyShipsVertically(enemyShipMatrix: List<List<EnemyShip>>, speed: Float): List<List<EnemyShip>> {
            return enemyShipMatrix.map {
                it.map { enemyShip ->
                    enemyShip.moveShip(YCoordinate(speed))
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