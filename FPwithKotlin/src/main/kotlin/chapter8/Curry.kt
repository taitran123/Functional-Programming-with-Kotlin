package chapter8

import Fun
import chapter1.compose
import chapter1.square
import chapter4.double

fun sum(a:Int):(Int)->Int = {b:Int-> a+b}
typealias Fun2<A, B,C> = (A,B) -> C
val sum = {a:Int, b: Int -> a+b}
val double:(Int) -> Int = {a:Int->2*a}
val square:(Int) -> Int = {a:Int->a*a}
val stringify: (Int) -> String = {a:Int->a.toString()}

infix fun <A, B> A.pipe(f:Fun<A,B>):B = f(this)

fun <A, B,C> Fun2<A,B,C>.curry(): (A)->(B)->C={
    a:A->{
        b:B->this(a,b)
}
}

fun comp(a:Int, b:Int):String {
    val currySum: (Int) -> (Int) -> Int = sum.curry()
    val doubleComposeSum: (Int)->(Int)->Int = double compose currySum
    val right: (Int) -> Int = doubleComposeSum(a)
    return (square compose right compose stringify)(b)
}


fun comp1(a: Int, b:Int):String{
    val right = (double compose sum.curry())(a)
    return (square compose right compose  stringify)(b)
}
fun main() {
    val curriedSum = sum.curry()

    val addThree = curriedSum(3)
    println(addThree)
    val result = addThree(4)
    println(result)
    println(comp(10,2))
    println(comp1(10,2))
}