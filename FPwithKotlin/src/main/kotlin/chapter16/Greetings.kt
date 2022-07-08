package chapter16

import chapter8.pipe
import java.util.Scanner

val readName: (World) -> Pair<String, World> = {w:World -> Scanner(System.`in`).nextLine() to w}

val printString: (String)->SideEffect ={s: String -> {a:World -> println(s) to World}}

fun askNameAndPrintGreeting(): (World)->World={
    w0:World ->
    val w1 = printString("What 's you name")(w0)
    val (name, w2) = readName(w1)
    printString("Hello $name ! \n")(w2)
}



fun main() {
    print("What 's your name ?")
    val name = Scanner(System.`in`).nextLine()
    println("hello, $name")
    readName(World) pipe ::println // 1
    printString("Hello Max \n")(World) pipe ::println
}

