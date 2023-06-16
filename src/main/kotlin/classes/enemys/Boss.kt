package classes.enemys

import classes.heroes.Hero

class Boss(name: String, hP: Int = 500, maxHP: Int = 500, damage: Int = 50) : Enemy(name, hP, maxHP, damage) {
    fun summon(bossHelper: BossHelper) {
        println("${this.name} beschwört seinen Lakaien.")
        bossHelper.active = true
        println(
            """^^^^^^^^^^^^^^^^^^^
        |/| ${bossHelper.name} |\
        |HP ${bossHelper.hP}/${bossHelper.maxHP}
        |Damage ${bossHelper.damage}
        |^^^^^^^^^^^^^^^^^^^
    """.trimMargin()
        )
    }

    override fun randomAction(enemyGroup: MutableList<Enemy>, heroGroup: MutableList<Hero>, bossHelper: BossHelper) {
        val randomizer =(1..100).random()
        if (enemyGroup.size == 2 || enemyGroup.size == 1 && bossHelper.wasActive) {
            if (this.hP < this.maxHP / 2 || bossHelper.hP < bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup)
                    in 21..40 -> areaAttack(heroGroup)
                    in 41..100 -> healing(enemyGroup)
                }
            } else if (this.hP > this.maxHP / 2 || bossHelper.hP > bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..40 -> attack(heroGroup)
                    in 41..80 -> areaAttack(heroGroup)
                    in 81..100 -> healing(enemyGroup)
                }
            }
        } else {
//            Damit der Helfer nur einmal beschworen werden kann hat der Helfer eine variable wasActive bekommen
            if (this.hP < this.maxHP / 2 && !bossHelper.wasActive) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup)
                    in 21..40 -> areaAttack(heroGroup)
                    in 41..70 -> healing(enemyGroup)
                    in 71..100 -> summon(bossHelper)
                }
            } else if (this.hP > this.maxHP / 2) {
                when (randomizer) {
                    in 1..30 -> attack(heroGroup)
                    in 31..60 -> areaAttack(heroGroup)
                    in 60..70 -> healing(enemyGroup)
                    in 71..100 -> summon(bossHelper)
                }
            }
        }
    }
}