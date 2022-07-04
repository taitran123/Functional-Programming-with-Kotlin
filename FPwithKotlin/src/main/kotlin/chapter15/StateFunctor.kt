package chapter15

import Fun
import chapter8.pipe

fun <S, A, B> State<S,A>.map(fn: Fun<A,B>):State<S,B> = State{it->
    val (a, newState) = this(it)
    fn(a) to newState
}

val skuSerial = {sku:String -> sku.takeLast(4)}
val skuState: State<Int, String> = State {state: Int -> "RAY-PROD-${String.format("%04d", state)}" to state+1}

val skuSerialState = skuState.map(skuSerial)

fun main() {
    skuState(0) pipe ::println
    skuSerialState(0) pipe ::println
}