package classes.inventory

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
            if (i.name=="Heiltrank"){

            println("${items.indexOf(i)+1}. ${i.name} +50 HP")
            } else {
                println("${items.indexOf(i)+1}. ${i.name} +20% SP")
            }
        }
    }
}