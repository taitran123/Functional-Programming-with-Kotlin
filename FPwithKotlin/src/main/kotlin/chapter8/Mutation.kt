package chapter8

data class MutationCounter(
        var count: Int = 1
)

val counter = MutationCounter()

fun squareWithMutationEffect(x: Int): Int{
    val result = x*x
    counter.count *= 10
    return result
}

fun doubleWithMutationEffect(x: Int): Int{
    val result = x*2
    counter.count /=2
    return result
}

typealias  Update<T> = (T) -> T

fun squareWithEffect(x: Int): Pair<Int, Update<MutationCounter>>{
    val result = x*x
    return result to {counter -> counter.count *10; counter}
}

fun doubleWithEffect(x : Int):Pair<Int, Update<MutationCounter>>{
    val result = 2*x
    return result to { counter-> counter.count /= 2; counter}
}

fun main() {
}