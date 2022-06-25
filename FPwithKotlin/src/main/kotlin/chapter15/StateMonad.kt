package chapter15

import chapter8.pipe

fun <A,S,B> State <S,A>.flatMap(fn: (A) -> State<S,B>): State<S, B> =
    State { s0:S -> val (a,s1) = this(s0)
        fn(a)(s1)}

val assignSkuWithState: (Product) -> State<Int, SkuProduct> = {
    prod: Product-> State(curriedAssignSku(prod))
}

fun main(){
    val prod1 = Product("1", "First Product")
    val initialState = State.lift<Int, Product>(prod1)
    val finalState = initialState.flatMap(assignSkuWithState)
    finalState(0) pipe ::println
}