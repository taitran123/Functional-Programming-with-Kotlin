package chapter6

fun recursiveFactorial1(n:Int):Int = when(n) {
    1 -> 1
    else -> n * recursiveFactorial1(n -1 )
}

fun tailRecFactorial(n:Int, fact: Int =1):Int= when(n)
{
    1-> fact
    else -> chapter6.tailRecFactorial(n-1, n*fact)
}
fun imperativeFactorial(n:Int):Int{
    var result = 1
    for (value in 2..n){
        result += value
    }
    return result
}

fun main() {
    println(tailRecFactorial(10))
}