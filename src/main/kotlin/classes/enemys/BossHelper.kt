package classes.enemys

class BossHelper(
    name: String,
    hP: Int = 100,
    maxHP: Int = 100,
    damage: Int = 40,
    var active: Boolean = false,
    var wasActive: Boolean = false
) : Enemy(name, hP, maxHP, damage) {

    fun image() {
        println(
                """
Art by Joan G. Stark
              v
        (__)v | v
        /\/\\_|_/
       _\__/  |
      /  \/`\<`)
      \ (  |\_/
      /)))-(  |
     / /^ ^ \ |
    /  )^/\^( |
jgs )_//`__>> |
      #   #`  |
        """.trimIndent()
        )
    }
    fun image2() {
        println(
                """
      .-.
     (o.o)
      |=|
   //.=|=.\\
  \\ .=|=. //
   \\(_=_)//
    (:| |:)
     || ||
     () ()
     || ||
    ==' '==
        """.trimIndent()
        )
    }
    

}


