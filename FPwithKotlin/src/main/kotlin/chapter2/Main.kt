package chapter2

import Fun

fun twice(a: Int):Int =a*2
fun format(b:Int):String = "Result is $b"
fun length(s: String): Int = s.length
fun formatAfterTwice(x: Int) = format(twice(x))
fun one(u:Unit):Int = 1
fun two(u:Unit):Int = 2
fun minusTree(u:Unit):Int = -3

fun main(){
    println(format(twice(37)))
    println(formatAfterTwice(37))

    //Proving Composition
    val f: Fun<Int, Int> = ::twice
    val g: Fun<Int, String> = ::format
    val formatTwice = g after f
    println(formatTwice(37))
    //Proving Associativity
    val h: Fun<String, Int> = ::length
    val leftSide = (h after g) after f
    val rightSide = h after (g after f)
    println("leftSide ${leftSide(37)} rightSide ${rightSide(37)}   ${leftSide(37) == rightSide(37)}")

    fun leftSide1(b:Int) = h(g(f(b)))
    fun leftSide2(b:Int) = h(g(f(b)))
    println("leftSide1 ${leftSide1(37)}")

//
    val twiceTwo = ::twice after ::two
    println(twiceTwo(Unit))
}

