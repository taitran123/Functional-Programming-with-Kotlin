package chapter6

import randomDelay
import kotlin.concurrent.thread

data class Counter(val counter: Int = 0)

fun incAndCheck(counter: Counter): Counter{
    randomDelay()
    val newCounter = Counter(counter.counter +1)
    randomDelay()
    if (newCounter.counter==2){
        println("Completed")
    }
    return counter
}

fun main() {
    val counter = Counter()
    lateinit var counter1:Counter
    val th1 = thread { counter1 = incAndCheck(counter) }
    th1.join()
    thread {
        incAndCheck(counter1)
    }
}