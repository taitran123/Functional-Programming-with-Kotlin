package chapter3

import Fun
import kotlin.random.Random

fun twice(x:Int):Int = x*2
fun twiceAndLog(x:Int):Int{
    val result = x*2
    println("2 * $x = $result")
    return  result
}

fun randomInc(a:Int) = a + Random.nextInt()
//Pure function are easy to understand
fun negative(x:Int) = -x
fun identity(x:Int) = x
fun abs(x:Int) = if (x < 0) negative(x) else x

fun main(){
    var f:Fun<Int, Int> = ::twice
    println("Executing twice(10)")
    f(10)
    println("Executing twiceAndLog(10)")
    f = ::twiceAndLog
    f(10)
}