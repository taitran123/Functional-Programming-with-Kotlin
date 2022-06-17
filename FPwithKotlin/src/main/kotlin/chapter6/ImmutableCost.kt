package chapter6

import randomDelay
import kotlin.concurrent.thread

fun mutableIncAndCheck(counter: MutableCounter){
    randomDelay()
    counter.count++
    randomDelay()
    if (counter.count==2){
        println("Completed")
    }
}

fun main() {
    val counter = MutableCounter()
    val th1 = thread { mutableIncAndCheck(counter) }
    th1.join()
    thread {
        mutableIncAndCheck(counter)
    }
}