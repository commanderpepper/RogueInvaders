package commanderpepper.screens

import com.badlogic.gdx.Game

class TitleScreen : Game() {
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