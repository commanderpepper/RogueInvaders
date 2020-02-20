package commanderpepper.objects.player.life

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate

class Life(private val xCoordinate: XCoordinate,
           private val yCoordinate: YCoordinate,
           private val amount: Int = 3) : Drawable {

    override fun draw() {
        spriteBatch.begin()
        font.draw(spriteBatch, "Lives: $amount", xCoordinate.value, yCoordinate.value)
        spriteBatch.end()
    }

    fun addLife(increase: Int = 1): Life {
        return Life(xCoordinate, yCoordinate, amount + increase)
    }

    fun removeLife(decrease: Int = 1): Life {
        return Life(xCoordinate, yCoordinate, amount - decrease)
    }

    fun isGameOver(): Boolean {
        return amount < 0
    }

    companion object {
        private val font = BitmapFont()
        private val spriteBatch = SpriteBatch()
    }
}