package commanderpepper.game

import com.badlogic.gdx.Game
import commanderpepper.screens.MainScreen

class MainGame : Game() {
    override fun create() {
        setScreen(MainScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        super.dispose()
    }

}