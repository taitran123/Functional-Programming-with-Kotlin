package chapter7

fun <T> FList<T>.size(): Int = match(
    whenNil = {0},
    whenCons = { head, tail -> 1 + tail.size() }
)

fun main() {
    println(FList.empty<Int>().head())
    println(FList.of(1).head())
    println(FList.of(1,2,3).head())
}