import classes.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy
import classes.heroes.Archer
import classes.heroes.Hero
import classes.heroes.Mage
import classes.heroes.Warrior

fun main() {
    val warrior = Warrior(warriorName)
    val mage = Mage(mageName)
    val archer = Archer(archerName)
    val boss = Boss(enemyName)
    val bossHelper = BossHelper("${boss.name}'s Lakai")

    val heroGroup: MutableList<Hero> = mutableListOf(warrior, mage, archer)
    var allHeroHP = heroGroup.sumOf { hero -> hero.hP }
    val enemyGroup: MutableList<Enemy> = mutableListOf(boss)
    var allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
    var bag = Bag()

    //    Hier wird das Spiel gestartet
    intro(warrior, mage, archer, boss)
    //    Hier wird überprüft, ob die Teams insgesamt noch HP besitzen.
    while (allHeroHP > 0 && allEnemyHP > 0) {
        //            Hier wird die Aktion jedes Helden ausgeführt, die noch in der Liste Helden sind(über HP verfügen)
        for (hero in heroGroup) {
            allHeroHP = heroGroup.sumOf { hero -> hero.hP }
            allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
            //        Hier wird überprüft, ob aus einem Team die gesamten Lebenspunkte verloren hat.
            //        Ist das der fall, wird das Spiel beendet.
            if (allHeroHP == 0) {
                println("GAME OVER\n${boss.name} hat alle Helden besiegt!")
                break
            } else if (allEnemyHP == 0) {
                println("SIEG\nDein Team hat ${boss.name} besiegt!")
                break
            }
            //        Hier wird ausgewählt, welche Aktion der jeweilige Held ausführen soll.
            hero.chooseAction(boss, bossHelper, enemyGroup, heroGroup, bag)
            if (bossHelper.hP == 0) {  //todo Prüft ob der Helfer bereits besiegt wurde
                bossHelper.wasActive = true //todo setzt die variable auf true damit er nur einmalig beschwört werden kann
            }
            //        Hier werden die Gegner entfernt, die keine Lebenspunkte mehr besitzen
            zeroHPOutEnemy(enemyGroup)
        }
        for (enemy in enemyGroup) {
            allHeroHP = heroGroup.sumOf { hero -> hero.hP } //todo evtl nicht notwendig
            allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP } //todo evtl nicht notwendig
            //            Hier wird die Aktion des Gegners ausgeführt sowie evlt. des Helfers
            if (allHeroHP == 0) {
                println("GAME OVER\n${boss.name} hat alle Helden besiegt!")
                break
            } else if (allEnemyHP == 0) {
                println("SIEG\nDein Team hat ${boss.name} besiegt!")
                break
            }
            enemy.randomAction(enemyGroup, heroGroup, bossHelper)
        }
        if (bossHelper.active && !bossHelper.wasActive) {
            enemyGroup.add(bossHelper)
        }
        //            Hier werden die Helden entfernt, die keine Lebenspunkte mehr besitzen
        zeroHPOutHero(heroGroup)
    }
}



