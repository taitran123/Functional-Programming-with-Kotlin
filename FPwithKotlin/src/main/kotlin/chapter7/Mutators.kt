package chapter7

fun <T> FList<T>.append(newItem :T):FList<T> = match(
    whenNil = {FList.of(newItem)},
    whenCons= {head, tail -> FCons(head, tail.append(newItem)) }
)

fun main() {
    val initialList = FList.of(1,2)
    val addedList = initialList.append(3)
    initialList.forEach {
        println("$it")
    }
    addedList.forEach {
        println("$it")
    }
}