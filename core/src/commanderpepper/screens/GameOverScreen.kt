package commanderpepper.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import commanderpepper.objects.player.score.Score

class GameOverScreen(private val game: Game) : Screen {

    private lateinit var font: BitmapFont
    private lateinit var spriteBatch: SpriteBatch

    override fun hide() {
    }

    override fun show() {
        font = BitmapFont()
        spriteBatch = SpriteBatch()
    }

    override fun render(delta: Float) {
        spriteBatch.begin()
        font.draw(spriteBatch, "GAME OVER", 225f, 450f)
        spriteBatch.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

}