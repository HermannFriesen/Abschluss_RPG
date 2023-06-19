package classes

import classes.heroes.Hero

open class Item(name: String) {

    open fun healing(groupOfHeroes: MutableList<Hero>) {
        println("Wer soll um 50 HP geheilt werden?")
        var listNum = 1
        for (hero in groupOfHeroes) {
            println("$listNum. ${hero.name} HP ${hero.hP}/${hero.maxHP}")
            listNum++
        }
        val inputUser = readln().toInt() //todo eingabe
        val target = groupOfHeroes[inputUser - 1]
        if (target.hP < target.maxHP) {
            val healHP = 50
            target.hP += healHP
            if (target.hP > target.maxHP) {
                target.hP = target.maxHP
            }
            println("${target.name} wird um $healHP geheilt und hat jetzt ${target.hP}/${target.maxHP}.")
        } else {
            println("${target.name} hat die Maximale HP erreicht.")
            healing(groupOfHeroes)
        }
    }

    open fun buff(groupOfHeroes: MutableList<Hero>) {
        println("Wer soll den angriffs Boost (10 %) für 3 Runden erhalten?")
        var listNum = 1
        for (hero in groupOfHeroes) {
            println("$listNum. ${hero.name} HP ${hero.hP}/${hero.maxHP}")
            listNum++
        }
        val inputUser = readln().toInt() //todo eingabe
        val hero = groupOfHeroes[inputUser - 1]
        val buff = hero.damage / 100 * 20
        hero.damage += buff
        hero.buff = true
        println("${hero.name} Schadenswert wird um $buff Schadenspunkte erhöht. Damage: ${hero.damage}.")

    }
}