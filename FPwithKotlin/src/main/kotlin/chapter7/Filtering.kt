package chapter7

typealias Predicate<T> = (T) -> Boolean

fun <T> FList<T>.filter(predicate: Predicate<T>): FList<T> = match(
    whenNil = { FList.empty()},
    whenCons = {head, tail ->
        if (predicate(head)){
            FCons(head, tail.filter(predicate))
        }else{
            tail.filter(predicate)
        }
    }
)

fun <T> FList<T>.take(n: Int): FList<T> = match(
    whenNil = { FList.empty()},
    whenCons = {head, tail ->
        if (n >0){
            FCons(head, tail.take(n-1))
        }else{
            FList.empty()
        }
    }
)

fun <T> FList<T>.takeLast(n: Int): FList<T> = match(
    whenNil = { FList.empty()},
    whenCons = {head, tail ->
        if (tail.size() >=n){
            tail.takeLast(n)
        }else{
           FCons(head, tail)
        }
    }
)

fun main() {
    FList.of(1,2,3,4,5,6,7,8,9)
        .takeLast(4)
//        .filter { it%3==0 }
        .forEach { println(it) }

}