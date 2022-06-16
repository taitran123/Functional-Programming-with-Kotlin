package chapter4.challenges

fun <A> myLazy(fn: () -> A?):()->A?{
    var result:A?=null
    return {
    if (result==null){
        result = fn()
    }
    result!!}
}


fun main(){
    val test:()->Int={ println("i am lazy"); 10 }
    var mylazy = myLazy(test)
    println(mylazy())
    println(mylazy())
    println(mylazy())
}