package classes.heroes

import GREEN
import RESETCOLOR

class Archer(name: String, hP: Int = 70, maxHP: Int = 70, damage: Int = 70, block: Boolean = false) :
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
        println(z1)
        println("$z2$GREEN           ($RESETCOLOR")
        println("$z3$GREEN             \\$RESETCOLOR")
        println("$z4$GREEN              )$RESETCOLOR")
        println("$z5$GREEN         ##-------->$RESETCOLOR")
        println("$z6$GREEN              )$RESETCOLOR")
        println("$z7$GREEN             /$RESETCOLOR")
        println("$z8$GREEN           ($RESETCOLOR")
        println(z9)
    }
    override var attackNameList: List<String> = listOf("Pfeilschuss","Pfeilhagel")

}