package classes.enemys

import classes.heroes.Hero

open class Enemy(
    var name: String = "Enemy",
    var hP: Int = 500,
    var maxHP: Int = 500,
    var damage: Int = 50,
    var block: Boolean = false
) {

    val attackNameList: MutableMap<String, Int> =
        mutableMapOf<String, Int>() //    todo liste der Individuellen Attacken und deren angriffsschaden

    //    soll eine zufällige Aktion durchführen
    open fun randomAction(enemyGroup: MutableList<Enemy>, heroGroup: MutableList<Hero>, bossHelper: BossHelper) {
        val randomizer = (1..100).random()
        // Unter die Hälfte der HP
        if (this.hP < this.maxHP / 2 || bossHelper.hP < bossHelper.maxHP / 2) {
            when (randomizer) {
                in 1..20 -> attack(heroGroup)
                in 21..40 -> areaAttack(heroGroup)
                in 41..100 -> healing(enemyGroup)
            }
        // Über die Hälfte der HP
        } else {
            when (randomizer) {
                in 1..40 -> attack(heroGroup)
                in 41..80 -> areaAttack(heroGroup)
                in 81..100 -> healing(enemyGroup)
            }
        }
    }

    //    Soll einen zufälligen Helden angreifen
    fun attack(target: MutableList<Hero>) {
        val randomTarget = target.random()
        if (randomTarget.block) {
            randomTarget.hP -= this.damage / 2
            hPToZero(randomTarget)
            println(
                """${this.name} greift ${randomTarget.name} mit Schwerthieb an (${this.damage}).
                    |${randomTarget.name} hat die Attacke blockiert!!
                |Die Attacke hat nur halben schaden zugefügt (${this.damage / 2}).
            """.trimMargin()
            )
            randomTarget.block = false
//todo statusanzeige evtl in funktion auslagern
            println(
                """....................
        |${randomTarget.name}
        |HP ${randomTarget.hP}/${randomTarget.maxHP}
        |Damage ${randomTarget.damage}
        |....................
    """.trimMargin()
            )
        } else {

            randomTarget.hP -= this.damage
            hPToZero(randomTarget)
            println("${this.name} greift ${randomTarget.name} mit Schwerthieb an (${this.damage}).")
//        todo statusanzeige evtl in funktion auslagern
            println(
                """....................
        |${randomTarget.name}
        |HP ${randomTarget.hP}/${randomTarget.maxHP}
        |Damage ${randomTarget.damage}
        |....................
    """.trimMargin()
            )
        }
    }

    fun areaAttack(target: MutableList<Hero>) {

        println("${this.name} greift alle Spieler mit Feuerlawine an (${this.damage}).")
        for (hero in target) {
            if (hero.block) {
                println("${hero.name} blockiert den Angriff von ${this.name}.")
                hero.hP -= this.damage / 2
                hPToZero(hero)
                println("${this.name} fügt ${hero.name} nur noch (${this.damage / 2}) schaden zu.")
                hero.block = false
                println(
                    """....................
        |${hero.name}
        |HP ${hero.hP}/${hero.maxHP}
        |Damage ${hero.damage}
        |....................
    """.trimMargin()
                )
            } else {
                hero.hP -= this.damage
                hPToZero(hero)
                println("${this.name} fügt ${hero.name} (${this.damage}) schaden zu.")
                hero.block = false
                println(
                    """....................
        |${hero.name}
        |HP ${hero.hP}/${hero.maxHP}
        |Damage ${hero.damage}
        |....................
    """.trimMargin()
                )
            }
        }
    }

    //    Soll alle Mitglieder die im Spiel sind, um 20 % ihrer gesamt HP heilen
    open fun healing(enemyList: MutableList<Enemy>) {
        if (enemyList.size == 2) {
            println("${this.name} heilt sich und sein Meister.")
            for (enemy in enemyList) {
                enemy.hP += maxHP / 100 * 20
                if (enemy.name == this.name) {
                    println("${this.name} heilt sich um ${maxHP / 100 * 20} HP.")
                } else {
                    println("${this.name} heilt sein Meister um ${maxHP / 100 * 20} HP.")
                }
            }
        } else {
            this.hP += maxHP / 100 * 20
            println("${this.name} heilt sich um ${maxHP / 100 *20} HP.")
        }
    }

    //      Blockiert einen Angriff mit einer 40 % Chance
    fun blocking() {
        var randomizer = (1..100).random()
        when (randomizer) {
            in 1..40 -> this.block = true
            else -> this.block = false
        }
    }
    //      Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)

    fun hPToZero(target: Hero) {
        if (target.hP < 0)
            target.hP = 0
    }
}
