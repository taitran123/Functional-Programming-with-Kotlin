package chapter4

fun greaterThan10(x:Int):Boolean{
    println("greaterThan10")
    return x>10
}

fun main(){
    val inputValue = 3
    val greater10 by lazy {greaterThan10(inputValue)}
    if (inputValue>4 && greater10){
        println("OK")
    }else{
        println("KO")
    }
}