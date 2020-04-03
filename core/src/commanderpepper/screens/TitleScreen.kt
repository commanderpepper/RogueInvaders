package commanderpepper.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import commanderpepper.Cursor
import commanderpepper.objects.Height
import commanderpepper.objects.Width
import commanderpepper.objects.XCoordinate
import commanderpepper.objects.YCoordinate
import commanderpepper.text.Text

class TitleScreen(private val game: Game) : Screen {

    private val titleTextXCoordinate = XCoordinate(200f)
    private val titleTextYCoordinate = YCoordinate(550f)
    private val titleText = Text("ROGUE INVADERS", titleTextXCoordinate, titleTextYCoordinate)

    private val newGameXCoordinate = XCoordinate(200f)
    private val newGameYCoordinate = YCoordinate(450f)
    private val newGame = Text("NEW GAME", newGameXCoordinate, newGameYCoordinate)

    private val optionsXCoordinate = XCoordinate(200f)
    private val optionsYCoordinate = YCoordinate(400f)
    private val optionsText = Text("OPTIONS", optionsXCoordinate, optionsYCoordinate)

    private val exitXCoordinate = XCoordinate(200f)
    private val exitYCoordinate = YCoordinate(300f)
    private val exitText = Text("EXIT", exitXCoordinate, exitYCoordinate)

    private val cursorXCoordinate = XCoordinate(180f)
    private val cursorYCoordinate = YCoordinate(440f)
    private val cursorHeight = Height(10f)
    private val cursorWidth = Width(10f)
    private val cursorColor = Color.MAGENTA
    private val cursor = Cursor(cursorColor, cursorXCoordinate, cursorYCoordinate, cursorHeight, cursorWidth)

    override fun hide() {
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        newGame.draw()
        optionsText.draw()
        exitText.draw()
        titleText.draw()
        cursor.draw()
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