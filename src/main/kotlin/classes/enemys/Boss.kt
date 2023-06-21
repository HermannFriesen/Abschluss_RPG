package classes.enemys

import classes.heroes.Hero

class Boss(
    name: String,
    hP: Int = 500,
    maxHP: Int = 500,
    damage: Int = 50,
    var poison: Boolean = false,
    var poisonCounter: Int = 3
) : Enemy(name, hP, maxHP, damage) {

    fun infoBox() {
        var z1 = ""
        z1 = z1.padEnd(20, ' ')
        var z2 = ""
        z2 = z2.padEnd(20, ' ')
        var z3 = "^^^^^^^^^^^^^^^^^^^"
        z3 = z3.padEnd(20, ' ')
        var z4 = this.name
        z4 = z4.padEnd(20, ' ')
        var z5 = "HP ${this.hP}/${this.maxHP}"
        z5 = z5.padEnd(20, ' ')
        var z6 = "Damage ${this.damage}"
        z6 = z6.padEnd(20, ' ')
        var z7 = "^^^^^^^^^^^^^^^^^^^"
        z7 = z7.padEnd(20, ' ')
        var z8 = ""
        z8 = z8.padEnd(20, ' ')
        println("$z1.-._                                             _,-,")
        println("$z2 `._`-._                                     _,-'_,'")
        println("$z3    `._  `-._        __.-----.__        _,-'  _,'")
        println("$z4       `._   `#==='\"'           '\"'===#'   _,'")
        println("$z5          `._/)  ._               _.  (\\_,'")
        println("$z6           )*'     **.__     __.**     '*(")
        println("$z7           #  .==..__  \"\"   \"\"  __..==,  #")
        println("$z8           #   `\"._(_).       .(_)_.\"'   #")
    }

    fun summonHelper(bossHelper: BossHelper) {
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

    override fun healing(enemyList: MutableList<Enemy>) {
        if (enemyList.size == 2) {
            println("${this.name} heilt sich und sein Lakai.")
            for (enemy in enemyList) {
                enemy.hP += maxHP / 100 * 20
                if (enemy.name == this.name) {
                    println("${this.name} heilt sich um ${maxHP / 100 * 20} HP.")
                } else {
                    println("${this.name} heilt sein Lakai um ${maxHP / 100 * 20} HP.")
                }
            }
        } else {
            this.hP += maxHP / 100 * 20
            println("${this.name} hat sich um 50 HP geheilt.")
        }
    }

    fun poisonHero() {
        this.poison = true
        println("${this.name} speit eine Giftwolke aus! Alle Helden verlieren ab jetzt 3 runden je 15 HP.")
    }

    override fun randomAction(enemyGroup: MutableList<Enemy>, heroGroup: MutableList<Hero>, bossHelper: BossHelper) {
        val randomizer = (1..100).random()
        //  Wenn der Helfer im Spiel ist oder der Boss alleine kämpft, nachdem der Helfer besiegt wurde
        if (enemyGroup.size == 1 && bossHelper.wasActive || enemyGroup.size == 2) {
            //  Wenn der Boss oder der Helfer nur noch die hälfte der Max HP besitzen, wird die boss Gruppe zu 60 % geheilt
            if (this.hP < this.maxHP / 2 || bossHelper.hP < bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup) // 20
                    in 21..40 -> areaAttack(heroGroup) // 20
                    in 41..100 -> healing(enemyGroup) // 60
                }
                //Sonst wenn Boss oder Helfer alleine kämpfen und über die hälfte der Max HP besitzen, kann der Gegner geheilt werden
            } else if (this.hP > this.maxHP / 2 || bossHelper.hP > bossHelper.maxHP / 2) {
                when (randomizer) {
                    in 1..30 -> attack(heroGroup) // 30
                    in 31..60 -> areaAttack(heroGroup) // 30
                    in 61..70 -> healing(enemyGroup) // 10
                    in 71..100 -> poisonHero() // 30
                }
            }
        } else {
            /*  Damit der Helfer nur einmal beschworen werden kann hat der Helfer eine variable wasActive bekommen.
                Solange diese falsch ist, kann der Boss den Helfer beschwören*/
            //  Auswahl der Attacken, wenn die HP des Bosses weniger als die hälfe der max HP beträgt
            if (this.hP < this.maxHP / 2 && !bossHelper.wasActive) {
                when (randomizer) {
                    in 1..20 -> attack(heroGroup) // 20
                    in 21..40 -> areaAttack(heroGroup) // 20
                    in 41..70 -> healing(enemyGroup) // 30
                    in 71..90 -> summonHelper(bossHelper) // 20
                    in 91..100 -> poisonHero() // 10
                }
                //  Auswahl der Attacken, wenn die HP des Bosses mehr als die hälfe der max HP beträgt (keine Heilung)
            } else if (this.hP > this.maxHP / 2 && !bossHelper.wasActive) {
                when (randomizer) {
                    in 1..30 -> attack(heroGroup) // 30
                    in 31..60 -> areaAttack(heroGroup) // 30
                    in 61..90 -> summonHelper(bossHelper) // 30
                    in 91..100 -> poisonHero() // 10
                }
            }
        }
    }

    fun image() {
        println(
            """
    .-._                                             _,-,
     `._`-._                                     _,-'_,'
        `._  `-._        __.-----.__        _,-'  _,'
           `._   `#==='"'           '"'===#'   _,'
              `._/)  ._               _.  (\_,'
               )*'     **.__     __.**     '*( 
               #  .==..__  ""   ""  __..==,  # 
Deelkar        #   `"._(_).       .(_)_."'   #
        """.trimIndent()
        )
    }

    fun image2() {
        println(
            """
                        ______
             ______,---'__,---'
         _,-'---_---__,---'
  /_    (,  ---____',
 /  ',,   `, ,-'
;/)   ,',,_/,'
| /\   ,.'//\
`-` \ ,,'    `.
     `',   ,-- `.
     '/ / |      `,         
     //'',.\_    .\\      ,{==>-
  __//   __;_`-  \ `;.__,;'
((,--,) (((,------;  `--' jv
        """.trimIndent()
        )
    }
}