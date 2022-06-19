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

fun <T> FList<T>.first() = head()
fun <T> FList<T>.last() = skip(size()-1).head()

fun <T> FList<T>.firstWithPredicate(predicate:Predicate<T>): T? = match(
        whenNil = {null},
        whenCons = {head, tail ->
            if (predicate(head)){
                head
            }else{
                tail.firstWithPredicate(predicate)
            }
        }
)


fun <T> FList<T>.takeLastWithPredicate(predicate: Predicate<T>):T? = filter(predicate).last()
fun <T> FList<T>.get(i:Int):T=skip(i).head()?:throw ArrayIndexOutOfBoundsException()


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
        .takeLast(1)
//        .filter { it%3==0 }
        .forEach { println(it) }
    val list =  FList.of(1,2,3,4,5,6,7,8,9)
    println("head ${list.firstWithPredicate { it%2==0 }}")
    println("last ${list.takeLastWithPredicate{ it%2==0 }}")

}