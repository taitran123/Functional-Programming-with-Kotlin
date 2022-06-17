package chapter5.challenge

inline fun <A, reified B> Array<A>.map(fn: (A) -> B): Array<B>
{
    val result = mutableListOf<B>()
    for (a in this){
        result.add(fn(a))
    }
    return result.toTypedArray()
}

fun main() {
    val square = { a: Int -> a * a }
    val toString = { a: Int -> "This is $a" }
//    arrayOf(1, 2, 3)
//        .map(square)
//        .forEach(::println)
    arrayOf(1, 2, 3)
        .map(toString)
        .forEach(::println)
}