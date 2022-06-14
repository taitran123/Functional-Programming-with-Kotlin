package chapter1

private val input = listOf("123", "ABC", "1ds","987", "adef","88", "101")
private fun declarativeSum(list: List<String>):Int = list.filter(::isValidNumber).map(String::toInt).sum()

private fun isValidNumber(str:String):Boolean{
    try {
        str.toInt()
        return true
    }catch (nfe : NumberFormatException){
        return false
    }
}

fun main(){
    println("Sum ${declarativeSum(input)}")
}