package chapter4

fun eagerEventSequence(n:Int):List<Int> = List(n) { i -> i * 2 }
fun evenPositiveStream(): () -> Int{
    var count = -2
    return {count+=2
        count}
}

fun main(){
//    println(eagerEventSequence(8))
    val evenPositive = evenPositiveStream()
    5.times{
        println(evenPositive())
    }
}

private fun Int.times(function: () -> Unit) {
    for (i in 0..this){
        function()
    }
}
