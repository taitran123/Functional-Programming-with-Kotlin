package chapter4

fun neverEnding():Int{
    while (true){}
}

fun double(x:Int): Int = x+x

fun main(){
    double(neverEnding())
}