package commanderpepper.objects.player.fireball

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
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
        val leeway = when {
            level in 61..120 -> 18f
            level > 121 -> 20f
            else -> 16f
        }
        return point.isInContact(height, enemyPoint, enemyHeight, leeway) && point.isInContact(width, enemyPoint, enemyWidth, leeway)
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
