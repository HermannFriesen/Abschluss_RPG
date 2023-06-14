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
    var active: Boolean = hP != 0
) {
    fun chooseAction(target1: Boss, target2: BossHelper) {
        println(
            """Welche Aktion soll ${this.name} ausführen:
            |1. Angriff
            |2. Flächenangriff
            |3. Nächsten Angriff blocken
            |4. Beutel benutzen
        """.trimMargin()
        )
        val inputUser = readln().toInt()
        if (inputUser == 1 && target2.active) {
            println(
                """Welchen Gegner wollen Sie Angreifen?
                |1. ${target1.name}
                |2. ${target2.name}
            """.trimMargin()
            )
            val targetInput = readln().toInt()
            if (targetInput == 1) {
                attack(target1)
            } else {
                attack(target2)
            }
        } else if (inputUser == 2 && target2.active) {
            areaAttack(target1, target2)
        } else if (inputUser == 2 && !target2.active) {
            areaAttack(target1,target2)
        } else if (inputUser == 1 && !target2.active) {
            attack(target1)
        } else if (inputUser == 3) {
            blocking()
        } else if (inputUser == 4) {
            useBag()
        }
    }

    open fun attack(target1: Enemy) {
        target1.blocking()
        if (target1.blocking()) {
            var damageAttack = this.damage
            target1.hP -= damageAttack / 2
            println(
                """${target1.name} hat die Attacke blockiert!!
                |Die Attacke hat nur den halben schaden zugefügt (${this.damage / 2}).
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
            println(
                """${this.name} greift ${target1.name} mit ${this.damage} an.)
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

    fun areaAttack(target1: Boss, target2: BossHelper) {
        if (target2.active) {
            attack(target1)
            attack(target2)
        } else{
            attack(target1)
        }
    }

    fun blocking(): Boolean {
        println("${this.name} versucht den nächsten Angriff zu blockieren.")
        return listOf(true, true, false).random()
    }

    fun useBag() {
        println("welches Item willst du verwenden")

    }
}