package chapter12

interface Monoid<T>{
    val unit:T
    val combine: (T,T) -> T
}