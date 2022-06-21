package chapter9

import chapter8.pipe

sealed class Either <out A, out B> {
    companion object{
        @JvmStatic
        fun <A> left(left: A): Either<A, Nothing> = Left(left)

        @JvmStatic
        fun <B> right(right: B): Either<Nothing, B> = Right(right)
    }
}

data class Left<A>(val left:A) : Either<A, Nothing>()
data class Right<B>(val right:B): Either<Nothing,B>()

fun <A,B,C,D> Either<A,B>.bimap(
        fl: (A) -> C,
        fr: (B) -> D
): Either<C,D> = when (this) {
    is Left<A> -> Either.left(fl(left))
    is Right<B> -> Either.right(fr(right))
}

fun strToIntEither(str:String):Either<java.lang.NumberFormatException, Int> = try{
    Either.right(str.toInt())
}catch (nfe: java.lang.NumberFormatException){
    Either.left(nfe)
}


fun <A, B, C> Either<A, B>.leftMap(fl:(A)->C):Either<C,B> = when (this){
    is Left<A> -> Either.left(fl(left))
    is Right<B> -> this
}

fun <A,B,D> Either<A,B>.rightMap(fr:(B)->D):Either<A,D> = when (this){
    is Right<B> -> Either.right(fr(right))
    is Left<A> -> this
}

fun <A, B> Either <A,B>.getOrDefault(defaultValue:B):B = when (this){
    is Left<A> -> defaultValue
    is Right<B> -> right
}

fun <A, B> Either<A,B>.getRightOrDefault(defaultValue: B):B = when(this){
    is Left<A> -> defaultValue
    is Right<B> -> right
}

fun <A, B> Either<A,B>.getLeftOrDefault(defaultValue: A):A = when(this){
    is Left<A> -> left
    is Right<B> -> defaultValue
}


fun <A, B> Either<A,B>.flip(): Either<B, A> = when (this){
    is Left<A> -> Either.right(left)
    is Right<B> -> Either.left(right)
}

fun <A, B,D> Either<A,B>.flatMap(
        fn: (B) -> Either<A, D>
): Either<A, D> = when (this) {
    is Left<A> -> Either.left(left)
    is Right<B> -> {
        val result = fn(right)
        when (result){
            is Left<A> -> Either.left(result.left)
            is Right<D> -> Either.right(result.right)
        }
    }
}

fun main(){
    val squareValue = {a:Int -> a*a}
    val formatError = {ex : Exception-> "Error ${ex.localizedMessage}"}
    strToIntEither("10").bimap(formatError, squareValue).getOrDefault(-1).pipe(::println)
    strToIntEither("10").bimap(formatError, squareValue).flip().getOrDefault("No Error!").pipe(::println)
    strToIntEither("10").rightMap(squareValue).getOrDefault(-1).pipe(::println)
    strToIntEither("10aaa").leftMap(formatError).getOrDefault("Generic Error").pipe(::println)
    println("Test===")
    strToIntEither("10").rightMap(squareValue).rightMap(Int::toString).flatMap(::strToIntEither).getOrDefault(-1).pipe(::println)
}