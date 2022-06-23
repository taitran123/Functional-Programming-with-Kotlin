package chapter14.tools

import chapter8.Fun
import chapter9.*

fun <A,B> Optional<A>.flatMap(fn: Fun<A, Optional<B>>):Optional<B> = when(this){
    is None -> Optional.empty()
    is Some<A> -> {
        val res = fn(value)
        when (res) {
            is None -> Optional.empty()
            is Some<B> -> Optional.lift(res.value)
        }
    }
}

fun <A, B, D> Either<A, B>.flatMap(fn: (B) -> Either<A, D>):Either<A,D> = when(this){
    is Left<A> -> Either.left(left)
    is Right<B> -> {
        when (val result = fn(right)) {
            is Left<A> -> Either.left(result.left)
            is Right<D> -> Either.right(result.right)
        }
    }
}