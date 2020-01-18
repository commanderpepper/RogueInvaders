package commanderpepper.interfaces

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

interface Drawable {

    val shapeRenderer: ShapeRenderer

    fun draw()
}