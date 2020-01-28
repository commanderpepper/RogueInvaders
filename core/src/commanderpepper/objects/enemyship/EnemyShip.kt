package commanderpepper.objects.enemyship

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle

class EnemyShip(
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

    companion object {
        val shapeRenderer = ShapeRenderer()
    }
}

fun createEnemyShipMatrix(rowSize: Int,
                          columnSize: Int,
                          verticalSpace: Float,
                          horizontalSpace: Float): List<List<EnemyShip>> {
    val shipMatrix = mutableListOf<MutableList<EnemyShip>>()

    val startingXCoordinate = XCoordinate(45f)
    val startingYCoordinate = YCoordinate(Gdx.graphics.height - 45f)
    var shipPoint = Point(startingXCoordinate, startingYCoordinate)

    val shipHeight = Height(8f)
    val shipWidth = Width(8f)

    for (row in 0..rowSize) {
        shipMatrix.add(mutableListOf())
        for (column in 0..columnSize) {
            val enemyShip = EnemyShip(shipPoint, shipHeight, shipWidth)
            shipMatrix[row].add(enemyShip)
            shipPoint = shipPoint.increaseXCoordiante(XCoordinate(horizontalSpace))
        }
        shipPoint = shipPoint.decreaseYCoordiante(YCoordinate(verticalSpace))
        shipPoint = Point(startingXCoordinate, shipPoint.yCoordinate)
    }

    return shipMatrix
}