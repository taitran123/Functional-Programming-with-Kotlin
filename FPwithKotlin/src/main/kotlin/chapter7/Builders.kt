package chapter7

fun <T> fListOf(vararg items:T):FList<T>{
    val tail = items.sliceArray(1 until items.size)
    println("tail ${tail.size}")
    return if (items.isEmpty()) Nil else FCons(items[0], fListOf(*tail))
}

fun main(){
    val fList = fListOf(1,2,3)
    println(fList)
}