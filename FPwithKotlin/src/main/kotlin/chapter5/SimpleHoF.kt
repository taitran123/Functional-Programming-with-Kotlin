package chapter5

fun Int.times (fn : (Int) ->Unit) = (1..this).forEach(fn)

fun main(){
    10.times{
        println(it)
    }
}