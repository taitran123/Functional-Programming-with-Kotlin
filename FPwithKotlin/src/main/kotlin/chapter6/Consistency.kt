package chapter6

data class MutableKey(val id:Int)

fun main() {
    val key1 = MutableKey(1)
    val key2 = MutableKey(2)
    val myMap = mutableMapOf(key1 to "First", key2 to "Second")
    println("Value for $key1 is ${myMap[key1]}")
//    key1.id = 2
    println("Value for $key1 is ${myMap[key1]} after key1 update")
    println("Value for $key2 is ${myMap[key2]}")
    println("The Map is $myMap")
    myMap.remove(key1).also { println("Remove $key1 from my Map") }
    myMap.remove(key2).also { println("Remove $key2 from my Map") }

    println("The map after remove is $myMap")
    println("Value for $key1 is ${myMap[key1]} after key1 remove")
    println("Value for $key2 is ${myMap[key2]} after key2 remove")
}