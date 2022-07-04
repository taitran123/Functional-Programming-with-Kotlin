package chapter4.mywork

import Fun

inline infix fun<A,B,C> Fun<B,C>.after(crossinline f:Fun<A,B>):Fun<A,C> = {
    this(f(it))
}
val double= {x:Int -> x*2}
val square= {x:Int -> x*x}
fun main() {
    val test =  square after double
    println(test(5))
}