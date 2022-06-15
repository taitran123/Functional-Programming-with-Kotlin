package chapter1

var count = 0 // 1
fun impure(value: Int): Int { // 2
    count++ // 3
    return value + count // 4
}

fun addOneAndLog(x: Int): Pair<Int, String> { // 1
    val result = x + 1
    return result to "New Value is $result" // 2
}
fun main(){

}