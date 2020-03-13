package commanderpepper.objects.player.fireball

import com.badlogic.gdx.graphics.Color

sealed class PlayerFireballLevel {
    class Off(val range: IntRange = 0..30, val color: Color = Color.GRAY) : PlayerFireballLevel()
    class Low(val range: IntRange = 35..90, val color: Color = Color.YELLOW) : PlayerFireballLevel()
    class Medium(val range: IntRange = 60..96, val color: Color = Color.ORANGE) : PlayerFireballLevel()
    class High(val color: Color = Color.RED) : PlayerFireballLevel()
}

sealed class EnemyFireballLevel {
    class Default(val color: Color = Color.PURPLE) : EnemyFireballLevel()
}