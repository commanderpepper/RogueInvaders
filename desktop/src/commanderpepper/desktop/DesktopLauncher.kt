package commanderpepper.spaceinvaders.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import commanderpepper.game.MainGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            title = "Rogue Invaders"
            height = 900
            width = 500
        }
        LwjglApplication(MainGame(), config)
    }
}