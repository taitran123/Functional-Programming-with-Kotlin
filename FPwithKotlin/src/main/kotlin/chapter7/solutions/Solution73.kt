package chapter7.solutions

import chapter7.FList
import chapter7.match

fun <T> FList<T>.forEachIndex1(fn: (Int,T)->Unit):Unit {
    fun FList<T>.loop(i:Int=0):Unit = match(
    whenNil = {},
    whenCons = {head, tail ->
        fn(i,head)
        tail.loop(i+1)
    }

    )
    loop()
}

fun main() {
    FList.of(1,2,3,4).forEachIndex1 { i, i2 -> println("$i $i2") }
}