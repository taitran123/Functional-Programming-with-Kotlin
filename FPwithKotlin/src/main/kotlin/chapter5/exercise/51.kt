package chapter5.exercise

public inline fun<T> Array<T>.first(predicate: (T)->Boolean):T{
    for (item in this){
        if(predicate(item)){
            return item
        }
    }
    throw NoSuchElementException("No matching predicate")
}


val test = arrayOf(1,2,3,4,5,6)

fun main(){
    println(test.first { it >7 })
}