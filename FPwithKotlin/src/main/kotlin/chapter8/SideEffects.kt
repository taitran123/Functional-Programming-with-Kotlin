package chapter8

import chapter1.compose

typealias Writer<A,B> = (A) ->Pair<B,String>

infix fun <A,B,C> Writer<A,B>.compose(g: Writer<B,C>):Writer<A,C> = {
    a:A->
    val (b, str) = this(a)
    val (c, str2) = g(b)
    c to "$str $str2"
}

val comFunWithWriter = ::functionWithWriter compose ::functionWithWriter

fun pureFunction(x:Int) = x*x-1

fun funcWithEffect(x:Int):Int{
    val result = x*x -1
    println("Result : $result")
    return result
}

fun functionWithWriter(x:Int):Pair<Int, String>{
    val result = x*x -1
    return result to "Result : $result"
}

fun main() {
//    pureFunction(5) pipe ::println
//    pureFunction(5) pipe ::println
//    pureFunction(5) pipe ::println

//    funcWithEffect(5) pipe ::println
//    funcWithEffect(5) pipe ::println
//    funcWithEffect(5) pipe ::println

//    listOf(1,2,3).map(::funcWithEffect) pipe ::println

//        listOf(1,2,3).map(::funcWithEffect).map(::funcWithEffect) pipe ::println
//        listOf(1,2,3).map(::funcWithEffect compose  ::funcWithEffect) pipe ::println

    val square = {a:Int -> a*a}
    val double = {a:Int -> a*2}
    val squareFunAndWrite = square compose ::functionWithWriter
    val doubleFunAndWrite = double compose ::functionWithWriter
    val compFunWithWriter = squareFunAndWrite compose doubleFunAndWrite
    compFunWithWriter(5).second pipe :: println
}