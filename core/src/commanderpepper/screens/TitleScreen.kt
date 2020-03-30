package commanderpepper.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate
import commanderpepper.text.NewGame

class TitleScreen(private val game: Game) : Screen {

    private val newGameXCoordinate = XCoordinate(200f)
    private val newGameYCoordinate = YCoordinate(450f)

    private val newGame = NewGame(newGameXCoordinate, newGameYCoordinate)

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        newGame.draw()
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