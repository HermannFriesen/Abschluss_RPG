import classes.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy
import classes.heroes.*
import com.sun.tools.javac.Main
import kotlin.system.exitProcess

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
    println(
        """
    @@@@@@@   @@@  @@@  @@@  @@@   @@@@@@@@  @@@@@@@@   @@@@@@   @@@  @@@  
    @@@@@@@@  @@@  @@@  @@@@ @@@  @@@@@@@@@  @@@@@@@@  @@@@@@@@  @@@@ @@@  
    @@!  @@@  @@!  @@@  @@!@!@@@  !@@        @@!       @@!  @@@  @@!@!@@@  
    !@!  @!@  !@!  @!@  !@!!@!@!  !@!        !@!       !@!  @!@  !@!!@!@!  
    @!@  !@!  @!@  !@!  @!@ !!@!  !@! @!@!@  @!!!:!    @!@  !@!  @!@ !!@!  
    !@!  !!!  !@!  !!!  !@!  !!!  !!! !!@!!  !!!!!:    !@!  !!!  !@!  !!!  
    !!:  !!!  !!:  !!!  !!:  !!!  :!!   !!:  !!:       !!:  !!!  !!:  !!!  
    :!:  !:!  :!:  !:!  :!:  !:!  :!:   !::  :!:       :!:  !:!  :!:  !:!  
     :::: ::  ::::: ::   ::   ::   ::: ::::   :: ::::  ::::: ::   ::   ::  
    :: :  :    : :  :   ::    :    :: :: :   : :: ::    : :  :   ::    :   
                                                                           
                                                                       
         @@@@@@@@@@    @@@@@@    @@@@@@   @@@@@@@  @@@@@@@@  @@@@@@@   
         @@@@@@@@@@@  @@@@@@@@  @@@@@@@   @@@@@@@  @@@@@@@@  @@@@@@@@  
         @@! @@! @@!  @@!  @@@  !@@         @@!    @@!       @@!  @@@  
         !@! !@! !@!  !@!  @!@  !@!         !@!    !@!       !@!  @!@  
         @!! !!@ @!@  @!@!@!@!  !!@@!!      @!!    @!!!:!    @!@!!@!   
         !@!   ! !@!  !!!@!!!!   !!@!!!     !!!    !!!!!:    !!@!@!    
         !!:     !!:  !!:  !!!       !:!    !!:    !!:       !!: :!!   
         :!:     :!:  :!:  !:!      !:!     :!:    :!:       :!:  !:!  
         :::     ::   ::   :::  :::: ::      ::     :: ::::  ::   :::  
          :      :     :   : :  :: : :       :     : :: ::    :   : :  
    """.trimIndent()
    )
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
    enemy.infoBox()
}

fun zeroHPOutHero(groupOfHero: MutableList<Hero>) {
    groupOfHero.removeAll { hero: Hero -> hero.hP == 0 }
}

fun zeroHPOutEnemy(groupOfEnemy: MutableList<Enemy>) {
    groupOfEnemy.removeAll { enemy: Enemy -> enemy.hP == 0 }
}

fun isBossHelperDefeated(bossHelper: BossHelper) {
    if (bossHelper.hP == 0) {       // Prüft ob der Helfer besiegt wurde
        bossHelper.wasActive = true // setzt die variable auf true damit er nur einmalig beschwört werden kann
    }
}

fun activateBossHelper(bossHelper: BossHelper, enemyGroup: MutableList<Enemy>) {
    if (bossHelper.active && !bossHelper.wasActive) {   // Prüft ob der Helfer nicht bereits beschworen wurde
        enemyGroup.add(bossHelper)                      // Wenn er noch nicht beschworen wurde, wird der Helfer...
    }                                                   // ...der Gegner Liste hinzugefügt
}

fun poisonCounter(groupOfHero: MutableList<Hero>, boss: Boss) {
    if (!boss.poison && boss.poisonCounter == 3) {
        boss.poison = false
    } else if (boss.poison && boss.poisonCounter == 0) {
        boss.poison = false
        println("Die Giftwolke von ${boss.name} ist verflogen!")
    } else {
        for (hero in groupOfHero) {
            hero.hP -= 15
            println("${hero.name}ist vergiftet und verliert 15 HP.")
        }
        boss.poisonCounter--
    }
}

fun bagUsed(bag: Bag) {
    if (bag.used)
        bag.used = false
}

fun buffCounter(hero: Hero) {
    if (!hero.buff && hero.buffCounter == 3) {
        hero.buff = false
    } else if (hero.buff && hero.buffCounter == 0) {
        hero.buff = false
        hero.damage = (hero.damage.toDouble() / 1.2).toInt()
        println("${hero.name}'s Vitamine hat die Wirkung verloren. ${hero.name} fügt nun wieder ${hero.damage} Schaden zu.")
    } else {
        hero.buffCounter--
    }
}

fun gameOver() {
    println(
        """                                             
                   @@@@@@@@   @@@@@@   @@@@@@@@@@   @@@@@@@@     @@@@@@   @@@  @@@  @@@@@@@@  @@@@@@@
                  @@@@@@@@@  @@@@@@@@  @@@@@@@@@@@  @@@@@@@@    @@@@@@@@  @@@  @@@  @@@@@@@@  @@@@@@@@
                  !@@        @@!  @@@  @@! @@! @@!  @@!         @@!  @@@  @@!  @@@  @@!       @@!  @@@
                  !@!        !@!  @!@  !@! !@! !@!  !@!         !@!  @!@  !@!  @!@  !@!       !@!  @!@
                  !@! @!@!@  @!@!@!@!  @!! !!@ @!@  @!!!:!      @!@  !@!  @!@  !@!  @!!!:!    @!@!!@!
                  !!! !!@!!  !!!@!!!!  !@!   ! !@!  !!!!!:      !@!  !!!  !@!  !!!  !!!!!:    !!@!@!
                  :!!   !!:  !!:  !!!  !!:     !!:  !!:         !!:  !!!  :!:  !!:  !!:       !!: :!!
                  :!:   !::  :!:  !:!  :!:     :!:  :!:         :!:  !:!   ::!!:!   :!:       :!:  !:!
                   ::: ::::  ::   :::  :::     ::    :: ::::    ::::: ::    ::::     :: ::::  ::   :::
                   :: :: :    :   : :   :      :    : :: ::      : :  :      :      : :: ::    :   : :
    """.trimIndent()
    )
}

fun victory() {
    println(
        """
                @@@  @@@  @@@   @@@@@@@  @@@@@@@   @@@@@@   @@@@@@@   @@@ @@@  
                @@@  @@@  @@@  @@@@@@@@  @@@@@@@  @@@@@@@@  @@@@@@@@  @@@ @@@  
                @@!  @@@  @@!  !@@         @@!    @@!  @@@  @@!  @@@  @@! !@@  
                !@!  @!@  !@!  !@!         !@!    !@!  @!@  !@!  @!@  !@! @!!  
                @!@  !@!  !!@  !@!         @!!    @!@  !@!  @!@!!@!    !@!@!   
                !@!  !!!  !!!  !!!         !!!    !@!  !!!  !!@!@!      @!!!   
                :!:  !!:  !!:  :!!         !!:    !!:  !!!  !!: :!!     !!:    
                 ::!!:!   :!:  :!:         :!:    :!:  !:!  :!:  !:!    :!:    
                  ::::     ::   ::: :::     ::    ::::: ::  ::   :::     ::    
                   :      :     :: :: :     :      : :  :    :   : :     :  
    """.trimIndent()
    )
}

fun startGame() {
    val warrior = Warrior(warriorName)
    val mage = Mage(mageName)
    val archer = Archer(archerName)
    val boss = Boss(enemyName)
    val bossHelper = BossHelper("${boss.name}'s Lakai")

    val heroGroup: MutableList<Hero> = mutableListOf(warrior, mage, archer)
    val enemyGroup: MutableList<Enemy> = mutableListOf(boss)
    var bag = Bag()

    //                Hier wird das Spiel gestartet
    intro(warrior, mage, archer, boss)
    //                Hier wird überprüft, ob die Teams insgesamt noch HP besitzen.
    var round = 1
    while (heroGroup.isNotEmpty() && enemyGroup.isNotEmpty()) {
        bagUsed(bag)
        //            Hier wird die Aktion jedes Helden ausgeführt, die noch in der Liste Helden sind(über HP verfügen)
        for (hero in heroGroup) {
            //        Hier wird der evtl.
            buffCounter(hero)
            //        Wenn alle helden besiegt
            if (enemyGroup.isEmpty()) {
                victory()
                break
            }
            //        Hier wird ausgewählt, welche Aktion der jeweilige Held ausführen soll.
            hero.chooseAction(boss, bossHelper, enemyGroup, heroGroup, bag)
            //        Prüft ob der Helfer besiegt wurde
            isBossHelperDefeated(bossHelper)
            //        Hier werden die Gegner entfernt, die keine Lebenspunkte mehr besitzen
            zeroHPOutEnemy(enemyGroup)
        }
        for (enemy in enemyGroup) {
            if (heroGroup.isEmpty()) {
                gameOver()
                break
            }
            //        Hier wird eine zufällige Aktion des Gegners (und des Helfers) ausgeführt
            enemy.randomAction(enemyGroup, heroGroup, bossHelper)
        }
        activateBossHelper(bossHelper, enemyGroup)
        poisonCounter(heroGroup, boss)
        //            Hier werden die Helden entfernt, die keine Lebenspunkte mehr besitzen
        zeroHPOutHero(heroGroup)
        round++
    }
}

fun restartGame() {
    println(
        """Wollen Sie das Spiel neu Starten?
        |1. Neustart
        |2. Beenden
    """.trimMargin()
    )
    // trychatch
    do {
        var inputUser: Int? = null
        try {
            inputUser = readln().toInt() // todo eingabe
            if (inputUser == 1) {
                startGame()
            } else if (inputUser == 2){
                exitProcess(0)
            }
        } catch (ex: Exception) {
            // Fehlerbehandlung nach Typ des Fehlers
            if (ex is NumberFormatException)
                println("Keine Zahl eingegeben. Bitte Zahl eingeben.")
            else if (ex is NullPointerException)
                println("Keine Eingabe! Bitte Zahl eingeben.")

        }
    } while (inputUser !in 1..2)
}
