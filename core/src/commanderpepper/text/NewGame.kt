package commanderpepper.text

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import commanderpepper.interfaces.Drawable
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate
import commanderpepper.objects.player.score.Score

class NewGame(private val xCoordinate: XCoordinate,
              private val yCoordinate: YCoordinate) : Drawable {

    override fun draw() {
        spriteBatch.begin()
        font.draw(spriteBatch, "NEW GAME", xCoordinate.value, yCoordinate.value)
        spriteBatch.end()
    }

    fun dispose() {
        font.dispose()
        spriteBatch.dispose()
    }

    companion object {
        private val font = BitmapFont()
        private val spriteBatch = SpriteBatch()
    }


}