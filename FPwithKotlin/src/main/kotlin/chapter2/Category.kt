package chapter2

import Fun
import Predicate


//infix allows you to use a syntax like g after f instead of g.after(f)
inline infix fun<A,B,C> Fun<B,C>.after(crossinline  f: Fun<A,B>):Fun<A,C> = {
    a:A -> this(f(a))
}
fun<A> identity(value: A) = value
fun <A> union(set1:Predicate<A>, set2:Predicate<A>):Predicate<A> ={
    set1(it)||set2(it)}

fun <A> intersection(set1: Predicate<A>, set2: Predicate<A>): Predicate<A> = {
    set1(it)&&set2(it)
}

//fun <A: Any> myLazy(fn: () -> A): () -> A