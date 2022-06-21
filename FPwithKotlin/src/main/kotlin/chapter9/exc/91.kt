package chapter9.exc

import chapter8.pipe

fun <T:Any> T.lift():T? = this
fun <T:Any> T.empty(): T? = null
fun <T:Any> T?.getOrDefault(default:T):T= this ?:default

fun main(){
    val opt = "10".lift()
    opt pipe ::println
    val strEmpty = String.getOrDefault("aaa")
    strEmpty pipe ::println

//
}