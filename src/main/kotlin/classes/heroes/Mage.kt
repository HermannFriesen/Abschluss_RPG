package classes.heroes

import BLUE
import RESETCOLOR

class Mage(name: String, hP: Int = 80, maxHP: Int = 80, damage: Int = 80, block: Boolean = false) :
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
        println("$z1$BLUE                  .$RESETCOLOR")
        println("$z2$BLUE                 /:\\$RESETCOLOR")
        println("$z3$BLUE                /;:.\\$RESETCOLOR")
        println("$z4$BLUE               //;:. \\$RESETCOLOR")
        println("$z5$BLUE              ///;:.. \\$RESETCOLOR")
        println("$z6$BLUE        __--\"////;:... \\\"--__$RESETCOLOR")
        println("$z7$BLUE      --__   \"--_____--\"   __--$RESETCOLOR")
        println("$z8$BLUE          '\"\"--_______--\"\"'$RESETCOLOR")
        println(z9)
    }

    override var attackNameList: List<String> = listOf("Blitzschlag","Flutwelle (AOE)")
}