package chapter15

import chapter14.tools.Chain3
import chapter14.tools.curry
import chapter8.pipe

fun replaceSuffix(input: String, lastToRemove: Int, postfix: String) = input.dropLast(lastToRemove) + postfix
val cReplaceSuffix = ::replaceSuffix.curry()

fun<S,T,R> State<S,T>.ap(fn: State<S, (T) -> R>): State<S, R> =
    State { s0:S ->
        val (t,s1) = this(s0)
        val (fnValue, s2) = fn(s1)
        fnValue(t) to s2
    }
infix  fun<S,A,B> State<S, (A) -> B>.appl(a: State<S,A>) = a.ap(this)

fun main() {
    val initialStateApp = State.lift<Int, Chain3<String, Int, String, String>>(cReplaceSuffix)
    val inputApp = State.lift<Int, String>("1234567890")
    val lastToRemoveApp = State.lift<Int, Int>(4)
    val postfixApp = State.lift<Int, String>("New")
    val finalStateApp = initialStateApp appl inputApp appl lastToRemoveApp appl  postfixApp
    inputApp(0) pipe ::println
    finalStateApp(0) pipe  ::println
}