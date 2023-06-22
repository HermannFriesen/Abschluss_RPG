package classes
import classes.heroes.Hero
open class Item(var name: String) {

    fun chooseHeroForItem(groupOfHeroes: MutableList<Hero>) {
        println("Welcher Held soll \"${this.name}\" bekommen?\n")
        println("0. zurück")
        var listNum = 1
        for (hero in groupOfHeroes) {
            if (this.name=="Heiltrank"){
            println("$listNum. ${hero.name} HP ${hero.hP}/${hero.maxHP}. (+50 HP)")
            listNum++
            } else {
                println("$listNum. ${hero.name} SP ${hero.damage}. (+20 % SP)")
                listNum++
            }
        }
    }


    fun healing(inputUserHero: Int, groupOfHeroes: MutableList<Hero>) {
            val hero = groupOfHeroes[inputUserHero - 1]
        if (hero.hP < hero.maxHP) {
            val healHP = 50
            hero.hP += healHP
            if (hero.hP > hero.maxHP) {
                hero.hP = hero.maxHP
            }
            println("${hero.name} wird um $healHP geheilt und hat jetzt ${hero.hP}/${hero.maxHP}.\n")
        } else {
            println("${hero.name} hat die maximal HP erreicht.\n")
        }
    }

    fun buff(inputUserHero: Int, groupOfHeroes: MutableList<Hero>) {
        val hero = groupOfHeroes[inputUserHero - 1]
        val buff = (hero.damage.toDouble() * 0.2).toInt()
        hero.damage += buff
        println("${hero.name}'s Schadenswert wird um $buff Schadenspunkte erhöht. Damage: ${hero.damage}.\n")
        hero.buff = true
    }
}