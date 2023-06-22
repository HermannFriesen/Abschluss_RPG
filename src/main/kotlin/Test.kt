import classes.inventory.Bag
import classes.enemys.Boss
import classes.enemys.BossHelper
import classes.enemys.Enemy
import classes.heroes.Archer
import classes.heroes.Hero
import classes.heroes.Mage
import classes.heroes.Warrior

fun main() {
    val warrior = Warrior(WARRIORNAME)
    val mage = Mage(MAGENAME)
    val archer = Archer(ARCHERNAME)
    val boss = Boss(ENEMYNAME)
    val bossHelper = BossHelper("${boss.name}'s Lakai")

    val heroGroup: MutableList<Hero> = mutableListOf(warrior, mage, archer)
    var allHeroHP = heroGroup.sumOf { hero -> hero.hP }
    val enemyGroup: MutableList<Enemy> = mutableListOf(boss)
    var allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
    val bag = Bag()

    //                Hier wird das Spiel gestartet
    intro(warrior, mage, archer, boss)
    //                Hier wird überprüft, ob die Teams insgesamt noch HP besitzen.
    var round = 1
    while (allHeroHP > 0 && allEnemyHP > 0) {
        bagUsed(bag)
        //            Hier wird die Aktion jedes Helden ausgeführt, die noch in der Liste Helden sind(über HP verfügen)
        for (hero in heroGroup) {
            allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
            //        Hier wird der evtl.
            buffCounter(hero)
            //        Wenn alle gegner besiegt wurden, wird die Schleife beendet
            if (allEnemyHP==0) {
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
            allHeroHP = heroGroup.sumOf { hero -> hero.hP }
            //            Hier wird die Aktion des Gegners ausgeführt sowie evlt. des Helfers
            if (allHeroHP==0) {
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
    restartGame()
}