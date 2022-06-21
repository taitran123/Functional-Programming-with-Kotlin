package chapter8

fun functionWithAnotherEffect(time: Long = System.currentTimeMillis(), x :Int): String{
    val result = x*x -1
    return "Result : $result calculated on $time"
}


inline infix fun <A,B,C> Fun<A,B>.compose(crossinline g: Fun<B,C>):Fun<A,C> = {
    a:A -> g(this(a))
}

fun main() {
    functionWithAnotherEffect(123L, 5) pipe  ::println
    functionWithAnotherEffect(123L, 5) pipe  ::println
}