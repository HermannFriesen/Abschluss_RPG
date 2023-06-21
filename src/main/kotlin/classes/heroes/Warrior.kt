package classes.heroes

import classes.enemys.Enemy

class Warrior(name: String, hP: Int = 100, maxHP: Int = 100, damage: Int = 50, block: Boolean = false) :
    Hero(name, hP, maxHP, damage, block) {
    fun image() {
        println(
                """
       .
      / \
      | |
      | |
      |.|
      |.|
      |:|
      |:|
    `--8--'
       8
       O
        """.trimIndent()
        )
    }
    fun image2() {
        println(
                """
     ,.
     \%`.
      `.%`.
        `.%`.
          `.%`.    __
            `.%`.  \ \
              `.%`./_/
                `./ /.
               __/\/:/;.
               \__/  `:/;. 
                  Krogg`:/ ;
                          `'
        """.trimIndent()
        )
    }
}