package chapter6

fun recAddMulti5(list:List<Int>):Int{
    fun loop(i:Int, sum:Int):Int = when{
        i==list.size -> sum
        list[i]%5==0 -> loop(i+1, sum+list[i])
        else ->  loop(i+1, sum)
    }
    return loop(0,0)
}


fun main() {
    val list = listOf(1,4,5,6,3,15,20)
    println(recAddMulti5(list))
}