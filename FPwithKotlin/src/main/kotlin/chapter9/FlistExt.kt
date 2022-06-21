package chapter9

import chapter7.FCons
import chapter7.FList
import chapter7.Nil
import chapter7.forEach
import chapter8.Fun
import chapter8.pipe

tailrec fun <T,S> FList<T>.fold(
        start:S,
        combineFunc: (S,T)->S
): S = when (this) {
    is Nil -> start
    is FCons<T> -> {
        tail.fold(combineFunc(start, head), combineFunc)
    }
}

fun <T,S> FList<T>.foldRight(
        start: S,
        combineFunc: (T, S) -> S
):S = when (this){
    is Nil -> start
    is FCons<T> -> {
        combineFunc(head, tail.foldRight(start, combineFunc))
    }
}

fun <T> FList<T>.append(rhs:FList<T>):FList<T> =
    foldRight(rhs) { item, acc -> FCons(item, acc) }

fun <T,S> FList<T>.map(fn: Fun<T,S>): FList<S> =
        when(this) {
            is Nil -> FList.empty()
            is FCons<T> -> FCons(fn(head), tail.map(fn))
        }

fun <T,S> FList<T>.flatMap(
        fn: Fun<T, FList<S>>):FList<S> = foldRight(FList.empty()){item, acc -> fn(item).append(acc)}

fun countUpToFList(value: Int) = FList.of(*Array(value){it})

fun main() {
    val number = FList.of(1,2,3,4,5,6)
    number.fold(0){acc, item-> acc+ item} pipe ::println
    number.fold(1){acc, item-> acc*item} pipe ::println
    FList.of(*("aabbccddeeffgghh".toCharArray().toTypedArray())).foldRight(StringBuilder()){ item, acc->
        acc.append(item)
        acc
    } pipe ::println

    FList.of(1,2,3,4,5,6,7).map ( ::double ).forEach( ::println )
    println("=============")
    val first = FList.of(1,2,3)
    val second = FList.of(4,5,6)
    first.append(second).forEach(::println)
    println("xxxx")
    var intList = FList.of(1,2,3)
    intList.flatMap(::countUpToFList).forEach(::println)
}