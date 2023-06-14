package classes.enemys

import classes.Bag
import classes.heroes.Hero

open class Enemy(
    var name:String="Enemy",
    var hP:Int = 500,
    var maxHP:Int= 500,
    var damage:Int= 50,
    var block: Boolean=false
) {
    fun attack(target: Enemy) {

    }

    fun areaAttack(target: List<Hero>) {

    }

    fun blocking(): Boolean {
        return this.block==listOf(true,true, false).random()
    }

    fun healing(target: Boss) {

    }
}