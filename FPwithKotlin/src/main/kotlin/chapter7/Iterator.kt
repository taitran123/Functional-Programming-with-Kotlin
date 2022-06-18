package chapter7

fun main() {
    listOf(1,2,3).forEach{
        println(it)
    }

    FList.of(1,2,3,5).forEach {
        println(it)
    }
}