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
        var numList = 1
        println("0. Zurück")
        for (item in items) {
            println("$numList. ${item.toString()}")
            numList++
        }
    }
    override fun toString(): String {
        return "${items.name}"
    }
    fun useItem(inputUser: Int, groupOfHeroes: MutableList<Hero>) {
        val chosenItem = items[inputUser - 1]
        if (chosenItem == Potion("Heiltrank")) {
            chosenItem.healing(groupOfHeroes)
        } else{
            chosenItem.buff(groupOfHeroes)
            items.remove(chosenItem)
        }
    }
}