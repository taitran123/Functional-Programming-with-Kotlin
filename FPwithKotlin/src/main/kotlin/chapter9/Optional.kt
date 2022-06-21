package chapter9

import Fun

sealed class Optional <out T>{
    companion object{
        @JvmStatic
        fun <T> lift(value:T): Optional<T> = Some(value)

        @JvmStatic
        fun <T> empty(): Optional<T> = None

    }
}

fun <A,B> Optional<A>.map(fn : Fun<A,B>):Optional<B> =
    when (this){
        is None -> Optional.empty()
        is Some<A> -> Optional.lift(fn(value))
    }

fun <A,B> Optional<A>.flatMap(fn: Fun<A, Optional<B>>):Optional<B> =
    when (this)
    {
        is None -> Optional.empty()
        is Some<A> -> {
            val res = fn(value)
            when (res) {
                is None -> Optional.empty()
                is Some<B> -> Optional.lift(res.value)
            }
        }
    }

fun <A> Optional<A>.getOrDefault(a:A):A=
    when (this){
        is None -> a
        is Some<A> -> value
    }

object None: Optional<Nothing>()
data class Some<T>(val value: T): Optional<T>()