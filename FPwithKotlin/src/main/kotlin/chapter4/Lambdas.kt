package chapter4

interface Combinabe<A>{
    fun combine(rh:A)
}

fun <A: Combinabe<A>> combine(lh:A, rh: A) = lh.combine(rh)
