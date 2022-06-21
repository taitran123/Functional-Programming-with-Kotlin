package chapter9.exc

import Fun

fun <A:Any, B:Any> A?.map(fn:Fun<A,B>):B?=
    if (this !=null) fn(this).lift() else null

fun <A:Any, B:Any> A?.flatmap(fn:Fun<A,B?>):B?=
    if (this !=null) fn(this)?.lift() else null
