package classes.heroes

class Mage(name:String,hP:Int=80,maxHP:Int=80,damage:Int=80,block:Boolean=false):Hero(name,hP,maxHP,damage,block) {
    fun image(){
        println("""
               //(,      
              ///(       
             ////((      
              ,,,        
    ...     /     .      
     #     /      .//    
     #     /    .  %(//  
     ,, ,///.   ,,,////  
     # (///(/    .(/((   
     # ((/( //   .#(     
     #      //  .//(     
     #         .//(/     
     #     /////(**/     
     #     ////(////     
     #  /////((//////  
        """.trimIndent())
    }
}