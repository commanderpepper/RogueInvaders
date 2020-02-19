package commanderpepper.objects.player.fireball

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Intersector
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle

class Fireball(
        private val level: Int = 0,
        private val point: Point,
        private val height: Height,
        private val width: Width
) : Rectangle(
        point, height, width
), Drawable {

    override fun draw() {
        shapeRenderer.apply {
            begin(ShapeRenderer.ShapeType.Filled)
            color = determineColor()
            rect(point.xCoordinate.value, point.yCoordinate.value, width.measurement, height.measurement)
            end()
        }
    }

    fun checkIfFireballYCoordinateIsTooHigh(): Boolean {
        val gdxHeight = Gdx.graphics.height.toFloat()
        return point.yCoordinate.value + height.measurement >= gdxHeight
    }

    fun createFireBall(fireballSpeed: YCoordinate): Fireball {
        return Fireball(level, point.increaseYCoordiante(fireballSpeed), height, width)
    }

    private fun determineColor(): Color {
        return determineFireballBarColor(level)
    }

    fun checkForEnemyCollision(enemyPoint: Point, enemyHeight: Height, enemyWidth: Width): Boolean {
        val fireballRect = com.badlogic.gdx.math.Rectangle(this.point.xCoordinate.value, this.point.yCoordinate.value, this.width.measurement, this.height.measurement)
        val enemyShipRect = com.badlogic.gdx.math.Rectangle(enemyPoint.xCoordinate.value, enemyPoint.yCoordinate.value, enemyWidth.measurement, enemyHeight.measurement)

        return rectOverlaps(fireballRect, enemyShipRect)
    }

    fun checkForCollision(otherPoint: Point, otherHeight: Height, otherWidth: Width): Boolean {
        val fireballRect = com.badlogic.gdx.math.Rectangle(this.point.xCoordinate.value, this.point.yCoordinate.value, this.width.measurement, this.height.measurement)
        val otherRect = com.badlogic.gdx.math.Rectangle(otherPoint.xCoordinate.value, otherPoint.yCoordinate.value, otherWidth.measurement, otherHeight.measurement)

        return rectOverlaps(fireballRect, otherRect)
    }

    /**
     * @return whether this rectangle overlaps the other rectangle.
     */
    private fun rectOverlaps(r1: com.badlogic.gdx.math.Rectangle, r2: com.badlogic.gdx.math.Rectangle): Boolean {
        return r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y
    }

    companion object {
        private val shapeRenderer = ShapeRenderer()
    }
}

fun createFireball(fireBallLevel: Int, fireBallPoint: Point, fireBallHeight: Height, fireBallWidth: Width): Fireball {
    return when {
        fireBallLevel in 61..120 -> {
            val nFireBallHeight = Height(fireBallHeight.measurement * 2f)
            Fireball(fireBallLevel, fireBallPoint, nFireBallHeight, fireBallWidth)
        }
        fireBallLevel > 121
        -> {
            val nFireBallHeight = Height(fireBallHeight.measurement * 3f)
            Fireball(fireBallLevel, fireBallPoint, nFireBallHeight, fireBallWidth)
        }
        else -> {
            Fireball(fireBallLevel, fireBallPoint, fireBallHeight, fireBallWidth)
        }
    }
}

class FireballLevel(value: Int = 1) {

}
