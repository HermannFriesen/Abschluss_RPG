package classes.heroes

import SLEEP_TIME
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
    open fun infoBox() {

    }

    open var attackNameList: List<String> = listOf()

    fun chooseAction(
        target1: Boss,
        target2: BossHelper,
        enemyList: MutableList<Enemy>,
        heroList: MutableList<Hero>,
        bag: Bag
    ) {
        if (bag.used) {
            this.infoBox()
            println(
                """Welche Aktion soll ${this.name} ausführen:
                    |
            |1. ${this.attackNameList[0]} (${this.damage} SP)
            |2. ${this.attackNameList[1]} (75 % = ${(this.damage-this.damage.toDouble() * 0.25).toInt()} AOE)
            |3. Nächsten Angriff blocken (-50 % SP)
            |
        """.trimMargin()
            )
            do {
                var inputUserAction: Int? = null
                try {
                    inputUserAction = readln().toInt()
                    if (inputUserAction == 1 && target2.hP > 0 && target2.active && !target2.wasActive) {
                        println(
                            """Welchen Gegner soll ${this.name} Angreifen?
                                |
                    |^^^^^^^^^^^^^^^^^^^
                |1. ${target1.name}
                |   HP ${target1.hP}/${target1.maxHP}
                |   ^^^^^^^^^^^^^^^^^^^
                |   
                |   /////////|\\\\\\\\\
                |2. /| ${target2.name} |\
                |   HP ${target2.hP}/${target2.maxHP}
                |   \\\\\\\\\|/////////
                |   
            """.trimMargin()
                        )
                        do {
                            var targetInput: Int? = null
                            try {
                                targetInput = readln().toInt()
                                when (targetInput) {
                                    1 -> {
                                        attack(target1)
                                    }

                                    2 -> {
                                        attack(target2)
                                    }

                                    else -> {
                                        println("Ungültige Zahl eingegeben. Bitte 1 oder 2 eingeben.")
                                    }
                                }
                            } catch (ex: Exception) {
                                println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
                            }
                        } while (targetInput !in 1..2)
                    } else if (inputUserAction == 2) {
                        areaAttack(enemyList)
                    } else if (inputUserAction == 1 && !target2.active || inputUserAction == 1 && target2.wasActive) {
                        attack(target1)
                    } else if (inputUserAction == 3) {
                        blocking()
                    } else {
                        println("Ungültige Zahl eingegeben. Bitte 1 bis 3 eingeben.")
                    }
                } catch (ex: Exception) {
                    println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
                }
            } while (inputUserAction !in 1..3)
        } else {
            this.infoBox()
            println(
                """Welche Aktion soll ${this.name} ausführen:
                    |
            |1. ${this.attackNameList[0]} (${this.damage} SP)
            |2. ${this.attackNameList[1]} (75 % = ${(this.damage-this.damage.toDouble() * 0.25).toInt()} AOE)
            |3. Nächsten Angriff blocken (-50 % SP)
            |4. Beutel benutzen
            |
        """.trimMargin()
            )
            do {
                var inputUserAction2: Int? = null
                try {
                    inputUserAction2 = readln().toInt()
                    if (inputUserAction2 == 1 && target2.hP > 0 && target2.active && !target2.wasActive) {
                        println(
                            """Welchen Gegner soll ${this.name} Angreifen?
                                |
                        |^^^^^^^^^^^^^^^^^^^
                |1. ${target1.name}
                |   HP ${target1.hP}/${target1.maxHP}
                |   ^^^^^^^^^^^^^^^^^^^
                |   
                |   /////////|\\\\\\\\\
                |2. /| ${target2.name} |\
                |   HP ${target2.hP}/${target2.maxHP}
                |   \\\\\\\\\|/////////
                |   
            """.trimMargin()
                        )
                        do {
                            var targetInput: Int? = null
                            try {
                                targetInput = readln().toInt()
                                when (targetInput) {
                                    1 -> {
                                        attack(target1)
                                    }

                                    2 -> {
                                        attack(target2)
                                    }

                                    else -> {
                                        println("Ungültige Zahl eingegeben. Bitte 1 oder 2 eingeben.")
                                    }
                                }
                            } catch (ex: Exception) {
                                println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
                            }
                        } while (targetInput !in 1..2)
                    } else if (inputUserAction2 == 2) {
                        areaAttack(enemyList)
                    } else if (inputUserAction2 == 1 && !target2.active || inputUserAction2 == 1 && target2.wasActive) {
                        attack(target1)
                    } else if (inputUserAction2 == 3) {
                        blocking()
                    } else if (inputUserAction2 == 4) {
                        useBag(target1, target2, enemyList, heroList, bag)
                    } else {
                        println("Ungültige Zahl eingegeben. Bitte 1 bis 4 eingeben.")
                    }
                } catch (ex: Exception) {
                    println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
                }
            } while (inputUserAction2 !in 1..4)
        }
    }

    open fun attack(target1: Enemy,attackName: String = attackNameList[0]) {
        target1.blocking()
        if (target1.block) {
            val damageAttack = this.damage
            target1.hP -= damageAttack / 2
            // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
            hPToZero(target1)
            println(
                """${target1.name} hat $attackName blockiert!!
                |Die Attacke hat nur halben schaden zugefügt (${this.damage / 2}).
                |
            """.trimMargin()
            )
            target1.block = false
            target1.infoBox()
        } else {
            target1.hP -= this.damage
            // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
            hPToZero(target1)
            println(
                """${this.name} greift ${target1.name} mit $attackName (${this.damage}) an.
                    |
            """.trimMargin()
            )
            target1.infoBox()
        }
    }

    //    Flächenschaden greift alle Gegner an und verursacht 75 % des Grundschadenwertes.
    fun areaAttack(enemyList: MutableList<Enemy>) {
        for (enemy in enemyList) {
            this.damage -= (this.damage.toDouble() * 0.25).toInt()
            attack(enemy,attackNameList[1])
            this.damage = (this.damage.toDouble() / 0.75).toInt()
        }
    }

    // Blockiert den nächsten Angriff des Gegners mit einer 70 % chance.
    fun blocking() {
        println("${this.name} versucht den nächsten Angriff zu blockieren.\n")
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
        println("welches Item willst du verwenden\n")
        bag.showBag()
        var inputUser: Int? = null
        do {
            try {
                inputUser = readln().toInt()

                when (inputUser) {
                    0 -> chooseAction(target1, target2, enemyList, heroList, bag)
                    in 1..bag.items.size -> {
                        useItem(target1, target2, enemyList, heroList, bag, inputUser)
                        break
                    }

                    else -> {
                        println("Ungültige Zahl eingegeben. Bitte 0 bis ${bag.items.size} eingeben.")
                    }
                }
            } catch (ex: Exception) {
                println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
            }
        } while (inputUser !in 0..bag.items.size)
    }


    fun useItem(
        target1: Boss,
        target2: BossHelper,
        enemyList: MutableList<Enemy>,
        heroList: MutableList<Hero>,
        bag: Bag,
        inputUserItem: Int
    ) {
        val chosenItem = bag.items[inputUserItem - 1]
        chosenItem.chooseHeroForItem(heroList)
        do {
            var inputUserHero: Int? = null
            try {
                inputUserHero = readln().toInt()
                if (inputUserHero == 0) {
                    this.useBag(target1, target2, enemyList, heroList, bag)
                } else if (chosenItem.name == "Heiltrank" && inputUserHero in 1..heroList.size && heroList[inputUserHero-1].hP < heroList[inputUserHero-1].maxHP) {
                    chosenItem.healing(inputUserHero, heroList)
                    bag.items.remove(chosenItem)
                    bag.used = true
                    break
                } else if (chosenItem.name == "Vitamine" && inputUserHero in 1..heroList.size) {
                    chosenItem.buff(inputUserHero, heroList)
                    bag.items.remove(chosenItem)
                    bag.used = true
                    break
                } else {
                    println("""Der Held ${heroList[inputUserHero-1].name} hat die Maximale HP erreicht 
                        |oder sie haben eine ungültige Zahl eingegeben. 
                        |Bitte 0 bis ${heroList.size} eingeben.""".trimMargin())
                    useItem(target1, target2, enemyList, heroList, bag, inputUserItem)
                }
            } catch (ex: Exception) {
                println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
            }
        } while (inputUserHero !in 0..heroList.size)
    }

    // Die Funktion hPToZero setzt minuswerte auf 0 (Ästhetik)
    fun hPToZero(target: Enemy) {
        if (target.hP < 0)
            target.hP = 0
    }
}
