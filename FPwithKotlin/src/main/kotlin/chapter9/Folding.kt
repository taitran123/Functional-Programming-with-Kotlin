package chapter9

import chapter1.compose
import chapter8.pipe
import chapter9.exc.map

fun List<Int>.imperativeSum():Int {
    var sum = 0
    for (i in 0 until size){
        sum += this[i]
    }
    return sum
}

fun List<Int>.declarativeSum():Int {
    tailrec fun helper(pos: Int, acc: Int): Int{
        if (pos==size){
            return acc
        }
        return helper(pos+1, this[pos]+acc)
    }
    return helper(0,0)
}

fun <T, S> List<T>.declarativeFold(start: S, combineFunc: (S,T) -> S):S{
    tailrec fun helper(post: Int, acc:S):S{
        if (post==size){
            return acc
        }
        return helper(post+1, combineFunc(acc, this[post]))
    }
    return helper(0,start)
}
fun <T, S> List<T>.declarativeFoldRight2(start: S, combineFunc: (T, S) -> S): S {
    fun helper(pos: Int): S {
        if (pos == size) {
            return start
        }
        return combineFunc(this[pos], helper(pos + 1))
    }
    return helper(0)
}


fun <T, S> List<T>.declarativeFoldRight(start: S, combineFunc: (T,S) -> S):S{
    tailrec fun helper(post: Int):S{
        if (post==size){
            return start
        }
        return combineFunc(this[post], helper(post+1))
    }
    return helper(0)
}

fun main() {
    val list = listOf(1,2,3,4,5,6,7,8,9,10)
    list.imperativeSum() pipe ::println
    println("Declarative")
    list.declarativeSum() pipe ::println
    list.declarativeFold(1){acc: Int, item: Int -> acc*item } pipe ::println
    list.declarativeFoldRight2(1){acc: Int, item: Int -> acc*item } pipe ::println
    list.map(Int::toString).declarativeFold(""){acc, item -> acc+item } pipe ::println
    list.map(Int::toString).declarativeFoldRight2(""){acc, item -> acc+item } pipe ::println

}