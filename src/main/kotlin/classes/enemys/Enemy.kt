package classes.enemys

import classes.Bag
import classes.heroes.Hero

open class Enemy(
    var name: String = "Enemy",
    var hP: Int = 500,
    var maxHP: Int = 500,
    var damage: Int = 50,
    var block: Boolean = false
) {
//    Soll einen Helden zufällig angreifen
    fun attack(target: MutableList<Hero>) {
        val randomTarget = target.random()
        println("")


    }
//    Soll zufällig Buffs verursachen
    fun strongAttack(target: Hero){

    }

    fun areaAttack(target: MutableList<Hero>) {

    }
//      Blockiert einen Angriff mit einer 40 % Chance
    fun blocking() {
        var randomizer = (1..100).random()
        when (randomizer) {
            in 1..40 -> this.block = true
            else -> this.block = false
        }
    }
//    Soll alle Mitglieder die noch im Spiel sind heilen
    fun healing(enemyList: MutableList<Enemy>) {

    }
}