package chapter4.challenges

fun fibo():()->Int{
    var first = 0
    var second = 1
    var count = 0
    println("I am lazy")
    return{
        val next = when(count){
            0->0
            1->1
            else->{
                val ret = first+second
                first=second
                second=ret
                ret
            }
        }
        count++
        next
    }
}

fun e():()->Double{
    var currentSum = 1.0
    var n = 1
    tailrec fun factorial(n :Int,tmp:Int):Int=
        if (n==1) tmp else factorial(n-1, n*tmp)
    return {
        currentSum += 1.0/factorial(n++,0).toDouble()
        currentSum
    }
}


private fun Int.times(function: () -> Unit) {
    for (i in 0..this){
        function()
    }
}

fun main() {
    val e =e()
    val fibo = fibo()
    5.times{
//        println(e())
        println(fibo())
    }

}