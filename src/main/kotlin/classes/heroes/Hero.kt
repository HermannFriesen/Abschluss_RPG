package classes.heroes

import classes.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy

open class Hero(
    var name: String = "Hero",
    var hP: Int = 100,
    var maxHP: Int = 100,
    var damage: Int = 50,
    var block: Boolean = false,
    var buff: Boolean = false,
    var buffCounter: Int = 3
) {
    //    todo liste der Individuellen Attacken und deren angriffsschaden
    val attackNameList: MutableMap<String, Int> = mutableMapOf<String, Int>()

    //    todo parameter löschen bzw überarbeiten
    fun chooseAction(target1: Boss, target2: BossHelper, enemyList: MutableList<Enemy>,heroList: MutableList<Hero>, bag: Bag) {
        println(
            """....................
        |${this.name}
        |HP ${this.hP}/${this.maxHP}
        |Damage ${this.damage}
        |....................
    """.trimMargin()
        )
//        todo auswahl funktion
        println(
            """Welche Aktion soll ${this.name} ausführen:
            |1. Angriff
            |2. Flächenangriff
            |3. Nächsten Angriff blocken
            |4. Beutel benutzen
        """.trimMargin()
        )
        val inputUser = readln().toInt()
        if (inputUser == 1 && target2.hP > 0 && target2.active && !target2.wasActive) {
            println(
                """Welchen Gegner wollen Sie Angreifen?
                    |^^^^^^^^^^^^^^^^^^^
                |1. ${target1.name}
                |   HP ${target1.hP}/${target1.maxHP}
                |   ^^^^^^^^^^^^^^^^^^^
                |   ^^^^^^^^^^^^^^^^^^^
                |2. /| ${target2.name} |\
                |   HP ${target2.hP}/${target2.maxHP}
                |   ^^^^^^^^^^^^^^^^^^^
            """.trimMargin()
            )
            val targetInput = readln().toInt()
            if (targetInput == 1) {
                attack(target1)
            } else {
                attack(target2)
            }
        } else if (inputUser == 2) {
            areaAttack(enemyList)
        } else if (inputUser == 1 && !target2.active || inputUser == 1 && target2.wasActive) {
            attack(target1)
        } else if (inputUser == 3) {
            blocking()
        } else if (inputUser == 4) {
            useBag(target1, target2, enemyList,heroList, bag)
        }
    }

    open fun attack(target1: Enemy) {
        target1.blocking()
        if (target1.block) {
            val damageAttack = this.damage
            target1.hP -= damageAttack / 2
            // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
            hPToZero(target1)
            println(
                """${target1.name} hat die Attacke blockiert!!
                |Die Attacke hat nur halben schaden zugefügt (${this.damage / 2}).
            """.trimMargin()
            )
            target1.block = false
            println(
                """^^^^^^^^^^^^^^^^^^^
        |${target1.name}
        |HP ${target1.hP}/${target1.maxHP}
        |Damage ${target1.damage}
        |^^^^^^^^^^^^^^^^^^^
    """.trimMargin()
            )
        } else {
            target1.hP -= this.damage
            // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
            hPToZero(target1)
            println(
                """${this.name} greift ${target1.name} mit ${this.damage} an.
            """.trimMargin()
            )
            println(
                """^^^^^^^^^^^^^^^^^^^
        |${target1.name}
        |HP ${target1.hP}/${target1.maxHP}
        |Damage ${target1.damage}
        |^^^^^^^^^^^^^^^^^^^
    """.trimMargin()
            )
        }
    }

    //    Flächenschaden greift alle Gegner an und verursacht 75 % des Grundschadenwertes.
    fun areaAttack(enemyList: MutableList<Enemy>) {
        for (enemy in enemyList) {
            this.damage / 100 * 75
            attack(enemy)
            this.damage / 75 * 100
        }
    }

    // Blockiert den nächsten Angriff des Gegners mit einer 70 % chance.
    fun blocking() {
        println("${this.name} versucht den nächsten Angriff zu blockieren.")
        return when ((1..70).random()) {
            in 1..100 -> this.block = true
            else -> this.block = false
        }
    }

    fun useBag(
        target1: Boss,
        target2: BossHelper,
        enemyList: MutableList<Enemy>,
        heroList: MutableList<Hero>,
        bag: Bag
    ) {
        println("welches Item willst du verwenden")
        bag.showBag()
        val inputUser = readln().toInt() //todo Eingabe
        if (inputUser == 0)
            chooseAction(target1, target2, enemyList, heroList, bag)
        else {
            bag.useItem(inputUser, heroList)
        }
    }

    // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
    fun hPToZero(target: Enemy) {
        if (target.hP < 0)
            target.hP = 0
    }
}
