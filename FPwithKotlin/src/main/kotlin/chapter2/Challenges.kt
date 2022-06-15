package chapter2

import Predicate


fun challenge1(){
    val evenIntSet:Predicate<Int> = {a:Int->a%2==0}
    println("0 is even ? ${evenIntSet(0)}")
    println("1 is even ? ${evenIntSet(1)}")
    println("2 is even ? ${evenIntSet(2)}")
    println("3 is even ? ${evenIntSet(3)}")
    println("4 is even ? ${evenIntSet(4)}")
}

fun challenge2(){
    val groupDivide2:Predicate<Int> = {a:Int->a%2==0}
    val groupDivide3:Predicate<Int> = {a:Int->a%3==0}
    val interSectionDivide23 = intersection(groupDivide2, groupDivide3)
    val unionDivide23 = union(groupDivide2, groupDivide3)


    println("6 is in intersection 2 &3 ?=> ${interSectionDivide23(6)}")
    println("7 is in union divide 2 &3 ?=> ${unionDivide23(7)}")
}



fun main(){
    challenge2()
}