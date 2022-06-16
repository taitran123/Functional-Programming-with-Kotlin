package chapter5

fun interface IsLarger<T> {
    fun isLarger(a:T, b:T):Boolean
}


//fun bubbleSort(values: IntArray) {
//    for (i in values.size - 1 downTo 0) {
//        for (j in 0 until i) {
//            if (values[j] > values[j + 1]) {
//                swap(values, j, j + 1)
//            }
//        }
//    }
//}

fun<T> bubbleSort(values: Array<T>, LargerStrategy:IsLarger<T>) {
    for (i in values.size - 1 downTo 0) {
        for (j in 0 until i) {
            if (LargerStrategy.isLarger(values[j] , values[j + 1])) {
                swap(values, j, j + 1)
            }
        }
    }
}
fun<T> swap(values: Array<T>, i: Int, j: Int){
    if (values[i] != values[j]) {
        val tmp = values[i]
        values[i] = values[j]
        values[j] = tmp
    }
}

fun main() {
    val array = arrayOf(15,2,10,6,9,45)
    val largerStrategy = IsLarger<Int> {a,b -> a>b}

//    bubbleSort(array){first, second ->
//        first>second
//    }
    bubbleSort(array, largerStrategy)
    array.printAll()
}

private fun<T> Array<T>.printAll() {
    for (i in this){
        println(i)
    }
}
