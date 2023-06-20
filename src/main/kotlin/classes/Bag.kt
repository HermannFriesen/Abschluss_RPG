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

    open fun showBag() {
        println("0. Zurück")
        for (i in items) {
            println("${items.indexOf(i)+1}. ${i.name}")
        }
    }
    fun useItem2(){

    }
}