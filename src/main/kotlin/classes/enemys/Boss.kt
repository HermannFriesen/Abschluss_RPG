package classes.enemys

import classes.heroes.Hero

class Boss(name: String, hP: Int = 500, maxHP: Int = 500, damage: Int = 50) : Enemy(name, hP, maxHP, damage) {
    fun summon(bossHelper: BossHelper,enemyGroup: MutableList<Enemy>) {
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
        val randomizer = (1..100).random()
        //  Wenn der Helfer im Spiel ist oder der Boss alleine kämpft, nachdem der Helfer besiegt wurde.
        if (enemyGroup.size == 1 && bossHelper.wasActive || enemyGroup.size == 2) {
            //  Wenn der Boss oder der Helfer nur noch die hälfte der Max HP besitzen, wird der Gegner zu 60 % geheilt.
            if (this.hP < this.maxHP / 2 || bossHelper.hP < bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup) // 20
                    in 21..40 -> areaAttack(heroGroup) // 20
                    in 41..100 -> healing(enemyGroup) // 60
                }
                //Sonst wenn Boss oder Helfer alleine kämpfen und die hälfte der Max HP besitzen, wird der Gegner geheilt.
            } else if (this.hP > this.maxHP / 2 || bossHelper.hP > bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..40 -> attack(heroGroup) // 40
                    in 41..80 -> areaAttack(heroGroup) // 40
                    in 81..100 -> healing(enemyGroup) // 20
                }
            }
        } else {
            /*  Damit der Helfer nur einmal beschworen werden kann hat der Helfer eine variable wasActive bekommen.
                Solange diese falsch ist, kann der Boss den Helfer beschwören.*/
            if (this.hP < this.maxHP / 2 && !bossHelper.wasActive) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup) // 20
                    in 21..40 -> areaAttack(heroGroup) // 20
                    in 41..70 -> healing(enemyGroup) // 10
                    in 71..100 -> summon(bossHelper,enemyGroup) // 30
                }
            } else if (this.hP > this.maxHP / 2 && !bossHelper.wasActive) {
                when (randomizer) {
                    in 1..30 -> attack(heroGroup) // 30
                    in 31..60 -> areaAttack(heroGroup) // 30
                    in 60..70 -> healing(enemyGroup) // 10
                    in 71..100 -> summon(bossHelper,enemyGroup) // 30
                }
            }
        }
    }
}