package commanderpepper.objects.player.fireball

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.*
import commanderpepper.objects.baseshapes.Rectangle

class Fireball(
        private val fireballProperty: FireballProperty,
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
        return Fireball(fireballProperty, point.increaseYCoordiante(fireballSpeed), height, width)
    }

    fun decrementFireball(): Fireball {
        return Fireball(fireballProperty.lower(), point, getSmallerHeight(), width)
    }

    private fun determineColor(): Color {
        return determineFireballBarColor(fireballProperty)
    }

    private fun determineFireballBarColor(fireballProperty: FireballProperty): Color {
        return when (fireballProperty) {
            is PlayerFireballLevel -> when (fireballProperty) {
                is PlayerFireballLevel.Off -> PlayerFireballLevel.Off().color
                is PlayerFireballLevel.Low -> PlayerFireballLevel.Low().color
                is PlayerFireballLevel.Medium -> PlayerFireballLevel.Medium().color
                else -> PlayerFireballLevel.High().color
            }
            else -> EnemyFireballLevel.Default().color
        }
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

    fun checkForFireballCollision(fireball: Fireball): Boolean {
        return fireball.checkForCollision(this.point, this.height, this.width)
    }

    fun isGreaterThanOne(): Boolean {
        return !(this.fireballProperty == PlayerFireballLevel.Low() || this.fireballProperty == PlayerFireballLevel.Off())
    }

    /**
     * @return whether this rectangle overlaps the other rectangle.
     */
    private fun rectOverlaps(r1: com.badlogic.gdx.math.Rectangle, r2: com.badlogic.gdx.math.Rectangle): Boolean {
        return r1.x < r2.x + r2.width && r1.x + r1.width > r2.x && r1.y < r2.y + r2.height && r1.y + r1.height > r2.y
    }

    private fun getSmallerHeight(): Height {
        return if (fireballProperty == PlayerFireballLevel.High()) {
            Height(height.measurement / 2f)
        } else {
            Height(height.measurement)
        }
    }

    companion object {
        private val shapeRenderer = ShapeRenderer()

        fun dispose() {
            shapeRenderer.dispose()
        }
    }
}

fun createPlayerFireball(fireBallLevel: Int, fireBallPoint: Point, fireBallHeight: Height, fireBallWidth: Width): Fireball {
    return when (fireBallLevel) {
        in PlayerFireballLevel.Low().range -> {
            Fireball(PlayerFireballLevel.Low(), fireBallPoint, fireBallHeight, fireBallWidth)
        }
        in PlayerFireballLevel.Medium().range -> {
            val nFireballHeight = Height(fireBallHeight.measurement * 2f)
            Fireball(PlayerFireballLevel.Medium(), fireBallPoint, nFireballHeight, fireBallWidth)
        }
        in PlayerFireballLevel.High().range -> {
            val nFireballHeight = Height(fireBallHeight.measurement * 3f)
            Fireball(PlayerFireballLevel.High(), fireBallPoint, nFireballHeight, fireBallWidth)
        }
        else -> {
            if (fireBallLevel in PlayerFireballLevel.Off().range) {
                Fireball(PlayerFireballLevel.Off(), fireBallPoint, fireBallHeight, fireBallWidth)
            } else {
                val nFireballHeight = Height(fireBallHeight.measurement * 3f)
                Fireball(PlayerFireballLevel.High(), fireBallPoint, nFireballHeight, fireBallWidth)
            }
        }
    }
}

//fun createFireball(fireballProperty: FireballProperty, fireBallPoint: Point, fireBallHeight: Height, fireBallWidth: Width): Fireball {
//    return when {
//        fireBallLevel in 61..120 -> {
//            val nFireBallHeight = Height(fireBallHeight.measurement * 2f)
//            Fireball(fireBallLevel, fireBallPoint, nFireBallHeight, fireBallWidth)
//        }
//        fireBallLevel > 121
//        -> {
//            val nFireBallHeight = Height(fireBallHeight.measurement * 3f)
//            Fireball(fireBallLevel, fireBallPoint, nFireBallHeight, fireBallWidth)
//        }
//        else -> {
//            Fireball(fireBallLevel, fireBallPoint, fireBallHeight, fireBallWidth)
//        }
//    }
//}

class FireballLevel(value: Int = 1) {

}
