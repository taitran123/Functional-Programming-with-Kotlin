package chapter7

//sealed class FList<out T>
//object Nil: FList<Nothing>()
internal data class FCons<T>(//4
    val head: T,
    val tail: FList<T> = Nil
): FList<T>()
//
//
//fun main() {
//    val empty = fListOf<Int>()
//    val singleElementFlist = FCons(2, fListOf())
//}

sealed class FList <out T>{
    companion object {//1
        @JvmStatic
        fun <T> of(vararg  items:T):FList<T>{//2
            val tail = items.sliceArray(1 until items.size)
            return if (items.isEmpty()){
                empty()
            }else{
                FCons(items[0], of(*tail))
            }
        }

        @JvmStatic
        fun <T> empty(): FList<T> = Nil//3
    }
}

internal object Nil:FList<Nothing>()//5


fun <T,S> FList<T>.match(
    whenNil: () -> S,
    whenCons: (head:T, tail: FList<T>) ->S
) = when (this){
    is Nil -> whenNil()
    is FCons<T> -> whenCons(head, tail)
}

fun <T> FList<T>.head(): T? = match(
    whenNil = {null},
    whenCons = {head, tail -> head }
)

fun <T> FList<T>.forEach(fn: (T)->Unit):Unit = match(
    whenNil = {},
    whenCons = {head, tail ->
        fn(head)
        tail.forEach(fn)
    }

)

fun <T> FList<T>.skip(n :Int):FList<T> = match(
        whenNil = { FList.empty()},
        whenCons = {head, tail ->
            if(n>0){
                tail.skip(n-1)
            }else{
                FCons(head, tail)
            }
        }
)

fun main() {
    val emptyList = FList.empty<Int>()
    val singleElementList = FList.of(1)
    val singleElementList2 = FCons(1, emptyList)
    val twoElementList = FList.of(1,2)
}
