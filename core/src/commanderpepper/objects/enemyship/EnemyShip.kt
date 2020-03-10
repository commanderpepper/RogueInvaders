package commanderpepper.objects.enemyship

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle
import commanderpepper.objects.player.fireball.Fireball

data class EnemyShip(
        private val id: Int,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(point, height, width), Drawable {

    private val enemyColor = Color.BLUE

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = enemyColor
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    fun moveShip(yCoordinate: YCoordinate): EnemyShip {
        return EnemyShip(this.id, Point(this.point.xCoordinate, this.point.yCoordinate + yCoordinate), this.height, this.width)
    }

    fun moveShip(xCoordinate: XCoordinate): EnemyShip {
        return EnemyShip(this.id, Point(this.point.xCoordinate + xCoordinate, this.point.yCoordinate), this.height, this.width)
    }

    fun checkIfEnemyShipIsTooLeft(): Boolean {
        return checkIfXCoordinateIsTooLeft(xCoordinate = point.xCoordinate)
    }

    fun checkIfEnemyShipIsTooRight(): Boolean {
        return checkIfXCoordinateIsTooRight(xCoordinate = point.xCoordinate, width = width)
    }

    fun checkForFireballCollision(fireball: Fireball): Boolean {
        return fireball.checkForEnemyCollision(point, height, width)
    }

    fun dispose() {
        shapeRenderer.dispose()
    }

    private fun getFireballXOrigin(fireballWidth: Width): XCoordinate {
        return XCoordinate(point.xCoordinate.value +
                ((width.measurement / 2) - (fireballWidth.measurement / 2)))
    }

    private fun getFireballYOrigin(): YCoordinate {
        return YCoordinate(point.yCoordinate.value)
    }

    fun getFireballPointOrigin(fireballWidth: Width): Point {
        return Point(getFireballXOrigin(fireballWidth), getFireballYOrigin())
    }

    companion object {
        val shapeRenderer = ShapeRenderer()
    }
}

fun createEnemyShipMap(rowSize: Int, columnSize: Int, verticalSpace: YCoordinate, sideMargin: XCoordinate, topMargin: YCoordinate,
                       shipHeight: Height = Height(12f), shipWidth: Width = Width(14f)): MutableMap<Int, EnemyShip> {
    val enemyShipMap : MutableMap<Int, EnemyShip> = mutableMapOf()

    val leftMostXCoordinate = sideMargin.copy()
    val rightMostXCoordinate = XCoordinate(Gdx.graphics.width - sideMargin.value)

    val verticalSpaceInBetween = XCoordinate((rightMostXCoordinate.value - leftMostXCoordinate.value) / columnSize)

    val startingYCoordinate = YCoordinate(Gdx.graphics.height.toFloat()) - topMargin
    var shipPoint = Point(leftMostXCoordinate, startingYCoordinate)

    var id = 0

    for(row in 0 until rowSize){
        for (column in 0 until columnSize){
            val enemyShip = EnemyShip(id, shipPoint, shipHeight, shipWidth)
            enemyShipMap[id] = enemyShip
            id++
            shipPoint = shipPoint.increaseXCoordiante(verticalSpaceInBetween)
        }
        shipPoint = shipPoint.decreaseYCoordiante(verticalSpace)
        shipPoint = Point(leftMostXCoordinate, shipPoint.yCoordinate)
    }

    return enemyShipMap
}

fun createEnemyShipMatrix(rowSize: Int,
                          columnSize: Int,
                          verticalSpace: YCoordinate,
                          sideMargin: XCoordinate,
                          topMargin: YCoordinate,
                          shipHeight: Height = Height(12f),
                          shipWidth: Width = Width(14f)): List<List<EnemyShip>> {
    val shipMatrix = mutableListOf<MutableList<EnemyShip>>()

    val leftMostXCoordinate = sideMargin.copy()
    val rightMostXCoordinate = XCoordinate(Gdx.graphics.width - sideMargin.value)

    val verticalSpaceInBetween = XCoordinate((rightMostXCoordinate.value - leftMostXCoordinate.value) / columnSize)

    val startingYCoordinate = YCoordinate(Gdx.graphics.height.toFloat()) - topMargin
    var shipPoint = Point(leftMostXCoordinate, startingYCoordinate)

    var id = 0

    for (row in 0 until rowSize) {
        shipMatrix.add(mutableListOf())
        for (column in 0 until columnSize) {
            val enemyShip = EnemyShip(id, shipPoint, shipHeight, shipWidth)
            id++
            shipMatrix[row].add(enemyShip)
            shipPoint = shipPoint.increaseXCoordiante(verticalSpaceInBetween)
        }
        shipPoint = shipPoint.decreaseYCoordiante(verticalSpace)
        shipPoint = Point(leftMostXCoordinate, shipPoint.yCoordinate)
    }

    return shipMatrix
}
