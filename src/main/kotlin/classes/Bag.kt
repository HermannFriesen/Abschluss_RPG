package classes

import classes.heroes.Buff
import classes.heroes.Hero

open class Bag(var used: Boolean = false) {
    var items = mutableListOf<Item>(
        Potion("Heiltrank"),
        Potion("Heiltrank"),
        Potion("Heiltrank"),
        Buff("Vitamine")
    )

    fun showBag() {
        println("0. Zurück")
        for (i in items) {
            println("${items.indexOf(i)+1}. ${i.name}")
        }
    }

    fun useItem(inputUser: Int, groupOfHeroes: MutableList<Hero>) {
        val chosenItem = items[inputUser - 1]
        if (chosenItem.name == "Heiltrank") {
            chosenItem.healing(groupOfHeroes)
        } else{
            chosenItem.buff(groupOfHeroes)
            items.remove(chosenItem)
        }
    }
}