package chapter6.challenge

tailrec fun fibo(n:Int, a:Int= 0, b:Int=1):Int =
    when(n){
        0->a
        1->b
        else-> fibo(n-1, b, a+b)
    }


fun main() {
    for (i in 0..10){
        println(fibo(i))
    }
}