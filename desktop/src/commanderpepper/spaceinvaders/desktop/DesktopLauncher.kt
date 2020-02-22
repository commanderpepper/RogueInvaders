package commanderpepper.spaceinvaders.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import commanderpepper.screens.MainScreen
import commanderpepper.screens.TitleScreen

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            title = "Space Invaders"
            height = 900
            width = 500
        }
//        LwjglApplication(MainScreen(), config)
        LwjglApplication(TitleScreen(), config)
    }
}