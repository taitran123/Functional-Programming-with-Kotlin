package chapter1

import Fun
import chapter2.after

fun double(x:Int):Int = 2*x
fun square(x: Int):Int = x*x
fun squareAndDouble(x:Int) = double(square(x))


inline infix fun <A,B,C> Fun<A,B>.compose(crossinline g: Fun<B,C>):Fun<A,C> = { a: A -> g(this(a))}
fun main(){
//    val result = squareAndDouble(10)
//    println(result)
    val double = {a : Int -> a*2}
    val square = {a:Int -> a*a}
    val stringify = Int::toString
    val stringifyDoubleSquareAfter = stringify after square after double
    println(stringifyDoubleSquareAfter(2))
    val stringifyDoubleSquareCompose = double compose square compose stringify
    println(stringifyDoubleSquareCompose(2))
}