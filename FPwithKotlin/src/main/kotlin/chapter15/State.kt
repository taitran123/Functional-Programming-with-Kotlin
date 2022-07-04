package chapter15

import chapter8.pipe

//typealias StateTransformer<S> = (S) -> S
data class State<S,T> (val st: StateTransformer<S,T>){
    companion object{
        @JvmStatic
        fun <S,T> lift(value:T):State<S,T> = State { state -> value to state }
    }
}
operator fun<S,T> State<S,T>.invoke(state: S) = st(state)

fun main() {
    val initialState = State.lift<Int, Product>(Product("1", "Cheese"))
    initialState pipe ::println
}