package chapter4

fun add(x:Int, y:Int):Int{
    val result = x+y
    println("add $result")
    return result
}

fun triple(x:Int):Int{
    val result = add(add(x,x), x)
    println("triple $result")
    return result
}

fun divide(x: Int, y: Int): Int{
    val result = x/y
    println("divide $result")
    return result
}

fun average(x: Int, y: Int):Int{
    val result = divide(add(x,y),2)
    println("average $result")
    return result
}

fun main(){
    println( triple(average(2,4)))
}