package classes.heroes
import classes.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy

open class Hero(
    var name: String="Hero",
    var hP: Int = 100,
    var maxHP: Int = 100,
    var damage: Int = 50,
    var active: Boolean = hP!=0
){
    fun chooseAction(target1: Boss,target2: BossHelper,){
        println("""wählen Sie eine Aktion aus:
            |1. Angriff
            |2. Flächenangriff
            |3. Nächsten Angriff blocken
            |4. Beutel benutzen
        """.trimMargin())
        val inputUser = readln().toInt()
        if (inputUser==1 && target2.active){
            println("""Welchen Gegner wollen Sie Angreifen?
                |1. ${target1.name}
                |2. ${target2.name}
            """.trimMargin())
            val targetInput= readln().toInt()
            if (targetInput==1){
            attack(target1)
            } else{
                attack(target2)
            }
        } else if (inputUser==2){
                areaAttack(target1,target2)
            } else if (inputUser==3){
                blocking()
        } else if (inputUser==4){
            useBag()
        }
    }
    open fun attack(target: Enemy) {
        if (target.blocking()){
            var damageAttack= this.damage
        target.hP-=damageAttack/2
            println("""${target.name}hat die Attacke blockiert!!
                |Die Attacke fügt nur noch den halben schaden zu (${this.damage/2})
            """.trimMargin())
            target.block=false
        } else {
            target.hP-=this.damage
            println("""${this.name} greift ${target.name} mit ${this.damage} an.)
            """.trimMargin())
        }
    }

    fun areaAttack(target1: Boss,target2: BossHelper) {
        if (target1.block&&target2.block){
            this.
        }

        }

    fun blocking(): Boolean {
        println("${this.name} versucht den nächsten Angriff zu blockieren.")
        return listOf(true,true, false).random()
    }

    fun useBag() {
        println("welches Item willst du verwenden")

    }
}