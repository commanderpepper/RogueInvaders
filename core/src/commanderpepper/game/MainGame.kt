package commanderpepper.game

import com.badlogic.gdx.Game
import commanderpepper.screens.MainScreen
import commanderpepper.screens.TitleScreen

class MainGame : Game() {
    override fun create() {
//        setScreen(MainScreen(this))
        setScreen(TitleScreen(this))
    }
}