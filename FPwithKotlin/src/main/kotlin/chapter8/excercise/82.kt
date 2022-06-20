package chapter8.excercise

fun<A,B,C> ((A,B)->C).flip():((B,A)->C)={
    b:B,a:A ->
    this(a,b)
}