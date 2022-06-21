package chapter9

import chapter9.exc.flatmap
import chapter9.exc.map

fun countUpTo(value: Int) = List(value){
    it}

fun main() {
    val emptyList = emptyList<Int>()
    val iniList = listOf(1,2,3)
    iniList.map(::double).forEach(::println)
    println("______")
    iniList.flatMap(::countUpTo).forEach(::println)
}