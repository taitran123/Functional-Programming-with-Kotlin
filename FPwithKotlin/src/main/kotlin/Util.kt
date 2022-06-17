import java.lang.Thread.sleep
import kotlin.random.Random

/** Utility function waiting a random interval */
fun randomDelay(max: Long = 100) =
    sleep(Random.nextLong(0, max))


/** Utility that measures the time for executing a lambda N times */
fun chrono(times: Int = 1, fn: () -> Unit): Long {
    val start = System.nanoTime()
    (1..times).forEach({ fn() })
    return System.nanoTime() - start
}