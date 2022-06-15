package chapter1

fun double(x:Int):Int = 2*x
fun square(x: Int):Int = x*x
fun squareAndDouble(x:Int) = double(square(x))

fun main(){
    val result = squareAndDouble(10)
    println(result)
}