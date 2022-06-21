package chapter9

import chapter8.pipe

fun strToInt(value: String): Optional<Int> =
    try {
        Optional.lift(value.toInt())
    }catch (nfe: NumberFormatException){
        Optional.empty()
    }


fun double(value: Int):Int = value *2

fun main1() {
    val res = strToInt("10x")
    when (res){
        is None -> println("Error")
        is Some<Int>->{
            val res2  = double(res.value)
            print(res2)
        }
    }
}

fun main() {
    Optional
        .lift("10")
        .flatMap(::strToInt) // 1
        .map(::double) // 2
        .getOrDefault(-1) // 3
        .pipe(::println)
    Optional
        .lift("10aa")
        .flatMap(::strToInt) // 1
        .map(::double) // 2
        .getOrDefault(-1) // 3
        .pipe(::println)
}