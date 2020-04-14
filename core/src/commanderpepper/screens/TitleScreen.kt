package commanderpepper.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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

    private var currentCursorYPosition: CursorYPosition = CursorYPosition.NewGamePosition()

    private val cursorXCoordinate = XCoordinate(180f)
    private val cursorYCoordinate = currentCursorYPosition.yCoordinate
    private val cursorHeight = Height(10f)
    private val cursorWidth = Width(10f)
    private val cursorColor = Color.MAGENTA

    private val cursorList = mutableListOf<Cursor>()

    override fun hide() {
    }

    override fun show() {
        cursorList.add(Cursor(cursorColor, cursorXCoordinate, cursorYCoordinate, cursorHeight, cursorWidth))
    }

    override fun render(delta: Float) {
        newGame.draw()
        optionsText.draw()
        exitText.draw()
        titleText.draw()

        val rightInput = Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)
        val leftInput = Gdx.input.isKeyJustPressed(Input.Keys.LEFT)
        val shootInput = Gdx.input.isKeyJustPressed(Input.Keys.SPACE)

        when {
            rightInput && leftInput -> Unit
            rightInput -> {
                currentCursorYPosition = currentCursorYPosition.moveDown()
                val newCursor = cursorList.first().move(currentCursorYPosition)
                cursorList.clear()
                cursorList.add(newCursor)
                println(cursorList)
            }
            leftInput -> {
                currentCursorYPosition = currentCursorYPosition.moveUp()
                val newCursor = cursorList.first().move(currentCursorYPosition)
                cursorList.clear()
                cursorList.add(newCursor)
                println(cursorList)
            }
        }

        cursorList.first().draw()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
    }

    sealed class CursorYPosition(val yCoordinate: YCoordinate) {
        class NewGamePosition(yCoordinate: YCoordinate = YCoordinate(440f)) : CursorYPosition(yCoordinate)
        class OptionPosition(yCoordinate: YCoordinate = YCoordinate(390f)) : CursorYPosition(yCoordinate)
        class ExitPosition(yCoordinate: YCoordinate = YCoordinate(290f)) : CursorYPosition(yCoordinate)
    }
}

private fun TitleScreen.CursorYPosition.moveUp(): TitleScreen.CursorYPosition {
    return when (this) {
        is TitleScreen.CursorYPosition.NewGamePosition -> TitleScreen.CursorYPosition.ExitPosition()
        is TitleScreen.CursorYPosition.OptionPosition -> TitleScreen.CursorYPosition.NewGamePosition()
        is TitleScreen.CursorYPosition.ExitPosition -> TitleScreen.CursorYPosition.OptionPosition()
    }
}

private fun TitleScreen.CursorYPosition.moveDown(): TitleScreen.CursorYPosition {
    return when (this) {
        is TitleScreen.CursorYPosition.NewGamePosition -> TitleScreen.CursorYPosition.OptionPosition()
        is TitleScreen.CursorYPosition.OptionPosition -> TitleScreen.CursorYPosition.ExitPosition()
        is TitleScreen.CursorYPosition.ExitPosition -> TitleScreen.CursorYPosition.NewGamePosition()
    }
}
