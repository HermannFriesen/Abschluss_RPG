package classes.heroes

import classes.enemys.Enemy

class Warrior(name:String,hP:Int=100,maxHP:Int=100,damage:Int=50):Hero(name,hP,maxHP,damage) {

    override fun attack(target: Enemy) {

    }
}