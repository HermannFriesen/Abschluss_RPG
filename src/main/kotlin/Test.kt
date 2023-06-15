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

    val heroGroup: MutableList<Hero> = mutableListOf(warrior, mage, archer)
    var allHeroHP = heroGroup.sumOf { hero -> hero.hP }
    val enemyGroup: MutableList<Enemy> = mutableListOf(boss)
    var allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
    var bossHelper = BossHelper("${boss.name}'s Lakei")

//    Hier wird das Spiel gestartet
    intro(warrior, mage, archer, boss)
//    Hier wird überprüft, ob die Teams insgesamt noch HP besitzen.
    while (allHeroHP > 0 && allEnemyHP > 0) {
//        Hier werden die Helden ausgeschlossen (entfernt), die keine Lebenspunkte mehr besitzen
        for (hero in heroGroup)
            if (hero.hP == 0){
                heroGroup.remove(hero)
            }
//        Hier wird die Aktion jedes Helden ausgeführt, die noch in der Liste Helden sind(über HP verfügen)
        for (hero in heroGroup) {
            allHeroHP = heroGroup.sumOf { hero -> hero.hP }
            allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
//            Hier wird überprüft, ob aus einem Team die gesamten Lebenspunkte verloren hat.
//            Ist das der fall, wird das Spiel beendet.
            if (allHeroHP == 0) {
                println("GAME OVER ${boss.name} hat alle Helden besiegt!")
                break
            } else if (allEnemyHP == 0) {
                println("Winning Dein Team hat ${boss.name} besiegt!")
                break
            }
//            Hier wird ausgewählt, welche Aktion der jeweilige Held ausführen soll.
            hero.chooseAction(boss, bossHelper,enemyGroup)
//            for (enemy in enemyGroup){
//                allHeroHP = heroGroup.sumOf { hero -> hero.hP }
//                allEnemyHP = enemyGroup.sumOf { enemy -> enemy.hP }
//                enemy.chooseAction(hero,hero,hero)
//            }
        }
    }
}

