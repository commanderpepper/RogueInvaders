package commanderpepper.objects


import com.badlogic.gdx.Gdx
import commanderpepper.interfaces.Drawable
import commanderpepper.interfaces.Updatable

private const val horizontalEnemyShipDistance = 35
private const val verticalEnemyShipDistance = 45

class EnemyShipController : Drawable, Updatable {

    private val enemyShipList = mutableListOf<EnemyShip>()

    override fun draw() {
        enemyShipList.forEach {
            it.draw()
        }
    }

    override fun update() {
        enemyShipList.forEach {
            it.update()
        }
    }

    fun createEnemyShips() {
        for (row in 1..3) {
            for (column in 0..9) {
                EnemyShip(horizontalEnemyShipDistance + column.toFloat() * horizontalEnemyShipDistance,
                        Gdx.graphics.height - row.toFloat() * verticalEnemyShipDistance,
                        1f,
                        0f).also {
                    enemyShipList.add(it)
                }
            }
        }

    }
}