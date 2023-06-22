package classes.heroes

import YELLOW
import RESETCOLOR

class Warrior(name: String, hP: Int = 100, maxHP: Int = 100, damage: Int = 50, block: Boolean = false) :
    Hero(name, hP, maxHP, damage, block) {

    override fun infoBox() {
        var z1 = "...................."
        z1 = z1.padEnd(25, ' ')
        var z2 = ""
        z2 = z2.padEnd(25, ' ')
        var z3 = ""
        z3 = z3.padEnd(25, ' ')
        var z4 = this.name
        z4 = z4.padEnd(25, ' ')
        var z5 = "HP ${this.hP}/${this.maxHP}"
        z5 = z5.padEnd(25, ' ')
        var z6 = "Damage ${this.damage}"
        z6 = z6.padEnd(25, ' ')
        var z7 = ""
        z7 = z7.padEnd(25, ' ')
        var z8 = ""
        z8 = z8.padEnd(25, ' ')
        var z9 = "...................."
        z9 = z9.padEnd(25,' ')
        println("$z1$YELLOW         ,.$RESETCOLOR")
        println("$z2$YELLOW         \\%`.$RESETCOLOR")
        println("$z3$YELLOW          `.%`.    __$RESETCOLOR")
        println("$z4$YELLOW            `.%`.  \\ \\$RESETCOLOR")
        println("$z5$YELLOW              `.%`./_/$RESETCOLOR")
        println("$z6$YELLOW                `./ /.)$RESETCOLOR")
        println("$z7$YELLOW               __/\\/:/;.$RESETCOLOR")
        println("$z8$YELLOW               \\__/  `:/ ;$RESETCOLOR")
        println("$z9$YELLOW                        `'$RESETCOLOR")
    }

    override var attackNameList: List<String> = listOf("Schwerthieb","Roundhouse Schwertschlag")

}