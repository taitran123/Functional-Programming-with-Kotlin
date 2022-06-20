package chapter8.excercise

fun <A, B, C> ((A)->(B)->C).uncurry():(A,B)->C={
    a:A, b:B->
    this(a)(b)
}