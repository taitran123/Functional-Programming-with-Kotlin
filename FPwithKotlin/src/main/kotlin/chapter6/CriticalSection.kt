package chapter6

import randomDelay
import kotlin.concurrent.thread

@Synchronized
fun syncMutableIncAndCheck(counter: MutableCounter){
    randomDelay()
    counter.count++
    randomDelay()
    if (counter.count==2){
        println("Completed")
    }
}

fun main() {
    val counter = MutableCounter()
    thread {
        syncMutableIncAndCheck(counter)
    }
    thread {
        syncMutableIncAndCheck(counter)
    }
}