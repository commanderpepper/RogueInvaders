package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.Color

interface FireballProperty {
    fun lower(): FireballProperty

    val range: IntRange
        get() = -2..-1
    val color: Color
}

sealed class PlayerFireballLevel : FireballProperty {
    data class Off(override val range: IntRange = 0..30, override val color: Color = Color.GRAY) : PlayerFireballLevel()
    data class Low(override val range: IntRange = 31..60, override val color: Color = Color.YELLOW) : PlayerFireballLevel()
    data class Medium(override val range: IntRange = 61..90, override val color: Color = Color.ORANGE) : PlayerFireballLevel()
    data class High(override val color: Color = Color.RED) : PlayerFireballLevel()

    override fun lower(): PlayerFireballLevel {
        return if (this == PlayerFireballLevel.High()) {
            PlayerFireballLevel.Medium()
        } else {
            PlayerFireballLevel.Low()
        }
    }
}

sealed class EnemyFireballLevel : FireballProperty {
    data class Default(override val color: Color = Color.PURPLE) : EnemyFireballLevel()

    override fun lower(): FireballProperty {
        return this
    }
}