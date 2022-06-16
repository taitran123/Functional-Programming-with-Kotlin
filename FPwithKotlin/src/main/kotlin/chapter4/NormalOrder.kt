package chapter4

fun addL(x: ()->Int,y: ()->Int): () -> Int{
    return {println("AddL");x()+y()}
}


fun tripleL(x: () -> Int):() -> Int{
    return {println("triple")
    addL(addL(x,x),x)()
    }
}

fun divideL(x: () -> Int, y: () -> Int): () -> Int {
    return { println("divideL"); x() / y() }
}
fun averageL(x: () -> Int, y: () -> Int): () -> Int {
    return { println("averageL"); divideL(addL(x, y)) { 2 }() }
}

fun main() {
    tripleL(averageL({ 2 }, { 4 }))()
}