package classes.enemys

class BossHelper(
    name: String,
    hP: Int = 100,
    maxHP: Int = 100,
    damage: Int = 40,
    var active: Boolean = false,
    var wasActive: Boolean = false
) : Enemy(name, hP, maxHP, damage) {

    override fun infoBox() {
        var z1 = "^^^^^^^^^^^^^^^^^^^"
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
        var z8 = "^^^^^^^^^^^^^^^^^^^"
        z8 = z8.padEnd(25, ' ')
        println("$z1      .-.")
        println("$z2     (o.o)")
        println("$z3      |=|")
        println("$z4   //.=|=.\\\\")
        println("$z5   \\\\(_=_)//")
        println("$z6    (:| |:)")
        println("$z7     || ||")
        println("$z8    ==' '==")
    }
}


