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
    val boss=Boss(enemyName)
    val heroGroup: List<Hero> = listOf(warrior,mage,archer)
    val enemyGroup: MutableList<Enemy> = mutableListOf(boss)
    val bossHelper=BossHelper("${boss.name}'s Helper")
    intro(warrior, mage, archer,boss)
}

