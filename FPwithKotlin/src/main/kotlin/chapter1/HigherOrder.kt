package chapter1

private fun Int.times(fn :()->Unit){
    for (i in 1..this){
        fn()
    }
}

fun main(){
    3.times({println("Hello World !!!")})
}