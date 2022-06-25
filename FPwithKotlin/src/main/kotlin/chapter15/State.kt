package chapter15


//typealias StateTransformer<S> = (S) -> S
typealias StateTransformer<S,T> = (S) -> Pair<T, S>
//data class State<S,T> (val st: StateTransformer<S,T>)

operator fun<S,T> State<S,T>.invoke(state:S) = st(state)

data class State<S,T>(val st: StateTransformer<S,T>){
    companion object{
        @JvmStatic
        fun<S,T> lift(value:T) : State<S,T> =
            State { state -> value to state }
    }
}

fun <S,A,B,C> State<S,A>.zip(s2:State<S,B>, combine: (A,B) -> C): State<S, C> =
    State {s0 ->
    val (v1,s1) = this(s0)
    val (v2, s2) = s2(s1)
    combine(v1,v2) to s2}