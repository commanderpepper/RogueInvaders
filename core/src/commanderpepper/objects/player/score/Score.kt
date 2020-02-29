package commanderpepper.objects.player.score

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate

class Score(private val xCoordinate: XCoordinate,
            private val yCoordinate: YCoordinate,
            private val score: Double = 0.0) : Drawable {

    fun increaseScore(addScore: Double): Score{
        return Score(xCoordinate, yCoordinate, score + addScore)
    }

    override fun draw() {
        spriteBatch.begin()
        font.draw(spriteBatch, "Score: $score", xCoordinate.value, yCoordinate.value)
        spriteBatch.end()
    }

    fun dispose(){
        font.dispose()
        spriteBatch.dispose()
    }

    companion object {
        private val font = BitmapFont()
        private val spriteBatch = SpriteBatch()
    }

}