package classes
import classes.heroes.Hero

class Item(name: String): Bag() {

    fun healing(target: Hero){
        if (target.hP<0){
            target.hP +=50
            println("${target.name} wird mit ${target.maxHP/2} geheilt und hat jetzt ${target.hP}.")
        } else{
            println("")
        }
    }
    fun buff(target: Hero){

    }
}