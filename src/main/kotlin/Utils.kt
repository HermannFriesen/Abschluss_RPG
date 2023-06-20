import classes.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy
import classes.heroes.Archer
import classes.heroes.Hero
import classes.heroes.Mage
import classes.heroes.Warrior

val warriorName = listOf<String>("Alvis", "Bjorn", "Bud", "Einar", "Ivar", "Jarl", "Orvar", "Ragnar").random()
val archerName =
    listOf<String>("Arjuna", "Abhimanyu", "Agilaz", "Apollo", "Arash", "Artemis", "Ashwathama", "Bhishma").random()
val mageName = listOf<String>(
    "Zathar",
    "Bartley",
    "Oswald",
    "Antero",
    "Shaka",
    "Podar",
    "Delina",
    "Hurin",
    "Mitra",
    "Kenneth",
    "Saliar",
    "Galadriel",
    "Lessthe",
    "Magnus"
).random()
val enemyName = listOf<String>(
    "Thornblight",
    "Skinrender",
    "Dreadnaught",
    "Morticia",
    "Mordath",
    "Wolftamer",
    "Typhus",
    "Corpsebreath",
    "Marroweater",
    "Abolusha",
    "Polyphemus",
    "Wintercall",
    "Bramblejack",
    "Hallowskull",
    "Tempest",
    "ScarRidge"
).random()

fun intro(warrior: Warrior, mage: Mage, archer: Archer, boss: Boss) {

    println("!Dungeon Master!")
    println("Press Enter to Start the Game")
    readln()
    generateHeroes(warrior, mage, archer)
    generateEnemy(boss)
}

fun generateHeroes(warrior: Warrior, mage: Mage, archer: Archer) {
    println("Generate Heroes to Play with...")
    println("Your Heroes are...")
    println(
        """....................
        |Der Warrior ${warrior.name}
        |HP ${warrior.hP}/${warrior.maxHP}
        |Damage ${warrior.damage}
        |....................
    """.trimMargin()
    )
    println(
        """....................
        |Der Mage ${mage.name}
        |HP ${mage.hP}/${mage.maxHP}
        |Damage ${mage.damage}
        |....................
    """.trimMargin()
    )
    println(
        """....................
        |Der Archer ${archer.name}
        |HP ${archer.hP}/${archer.maxHP}
        |Damage ${archer.damage}
        |....................
    """.trimMargin()
    )
}

fun generateEnemy(enemy: Boss) {
    println("You will fight against...")
    println(
        """^^^^^^^^^^^^^^^^^^^
        |${enemy.name}
        |HP ${enemy.hP}/${enemy.maxHP}
        |Damage ${enemy.damage}
        |^^^^^^^^^^^^^^^^^^^
    """.trimMargin()
    )
}

fun zeroHPOutHero(groupOfHero: MutableList<Hero>) {
    groupOfHero.removeAll { hero: Hero -> hero.hP == 0 }
//    val heroesToRemove = groupOfHero
//    for (hero in heroesToRemove) {
//        if (hero.hP == 0) {
//            groupOfHero.remove(hero)
//        }
//    }
}

fun zeroHPOutEnemy(groupOfEnemy: MutableList<Enemy>) {
    groupOfEnemy.removeAll { enemy: Enemy -> enemy.hP == 0 }
}
fun bagUsed(bag: Bag){
    if (bag.used)
        bag.used=false
}
//fun buffreset(rounds: Int,groupOfHero: MutableList<Hero>,groupOfEnemy: MutableList<Enemy>){
//
//    val roundNumStart = 0
//
//    if (roundNumStart){
//
//    }


//}

