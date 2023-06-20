package classes

import classes.heroes.Hero
import kotlin.math.roundToInt

open class Item(var name: String) {

    fun chooseHeroForItem(groupOfHeroes: MutableList<Hero>, bag: Bag) {
        println("Welcher Held soll \"${this.name}\" bekommen?")
        println("0. zurück")
        var listNum = 1
        for (hero in groupOfHeroes) {
            println("$listNum. ${hero.name} HP ${hero.hP}/${hero.maxHP}")
            listNum++
        }
    }


    fun healing(inputUser: Int,groupOfHeroes: MutableList<Hero>, bag: Bag) {
            val hero = groupOfHeroes[inputUser - 1]
        if (hero.hP < hero.maxHP) {
            val healHP = 50
            hero.hP += healHP
            if (hero.hP > hero.maxHP) {
                hero.hP = hero.maxHP
            }
            println("${hero.name} wird um $healHP geheilt und hat jetzt ${hero.hP}/${hero.maxHP}.")
        } else {
            println("${hero.name} hat die maximal HP erreicht.")
            chooseHeroForItem(groupOfHeroes, bag)
        }
    }

    fun buff(inputUser: Int, groupOfHeroes: MutableList<Hero>) {
        val hero = groupOfHeroes[inputUser - 1]
        val buff = (hero.damage.toDouble() * 0.2).toInt()
        hero.damage += buff
        println("${hero.name} Schadenswerte wird um $buff Schadenspunkte erhöht. Damage: ${hero.damage}.")
        hero.buff = true
    }
}