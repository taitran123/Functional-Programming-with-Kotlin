package chapter6

import randomDelay
import kotlin.concurrent.thread

data class MutableCounter(
    var count:Int = 0
)

val counter = MutableCounter()
val task = {
    randomDelay()
    counter.count++
    randomDelay()
    if (counter.count==2){
        println("Completed")
    }
}

fun main() {
    thread(block = task)
    thread(block = task)

}